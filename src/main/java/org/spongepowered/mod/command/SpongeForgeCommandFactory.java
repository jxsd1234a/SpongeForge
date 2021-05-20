/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod.command;

import static org.spongepowered.api.command.args.GenericArguments.optional;
import static org.spongepowered.api.command.args.GenericArguments.plugin;

import com.google.common.collect.Lists;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.common.SpongeImpl;
import org.spongepowered.common.command.SpongeCommandFactory;
import org.spongepowered.common.command.args.FilteredPluginsCommandElement;
import org.spongepowered.mod.plugin.SpongeModPluginContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SpongeForgeCommandFactory extends SpongeCommandFactory {
    private static final List<String> MOD_LIST_STATICS = Lists.newArrayList("minecraft", "mcp", "FML", "forge", "spongeapi", "sponge");

    public static CommandSpec createSpongeModsCommand() {
        return CommandSpec.builder()
                .description(Text.of("List currently installed mods"))
                .permission("sponge.command.mods")
                .arguments(optional(new FilteredPluginsCommandElement(Text.of("mod"), getFilteredModsPredicate())))
                .executor((src, args) -> {
                    if (args.hasAny("mod")) {
                        sendContainerMeta(src, args,  "mod");
                    } else {
                        final Collection<PluginContainer> containers = SpongeImpl.getGame().getPluginManager().getPlugins();
                        final List<PluginContainer> sortedContainers = new ArrayList<>();

                        // Add static listings first
                        MOD_LIST_STATICS.forEach(containerId -> containers.stream()
                                .filter(container -> container.getId().equalsIgnoreCase(containerId))
                                .findFirst()
                                .ifPresent(sortedContainers::add));

                        containers.stream()
                                .filter(container -> !MOD_LIST_STATICS.contains(container.getId()) && !(container instanceof
                                        SpongeModPluginContainer))
                                .sorted(Comparator.comparing(PluginContainer::getName))
                                .forEachOrdered(sortedContainers::add);

                        if (src instanceof Player) {
                            final List<Text> containerList = new ArrayList<>();

                            final PaginationList.Builder builder = PaginationList.builder();
                            builder.title(Text.of(TextColors.RED, "Mods", TextColors.WHITE, " (", sortedContainers.size(), ")"))
                                    .padding(Text.of(TextColors.DARK_GREEN, "="));

                            for (PluginContainer container : sortedContainers) {
                                final Text.Builder containerBuilder = Text.builder()
                                        .append(Text.of(TextColors.RESET, " - ", TextColors.GREEN, container.getName()))
                                        .onClick(TextActions.runCommand("/sponge:sponge mods " + container.getId()))
                                        .onHover(TextActions.showText(Text.of(
                                                TextColors.RESET,
                                                "ID: ", container.getId(), Text.NEW_LINE,
                                                "Version: ", container.getVersion().orElse("Unknown"))));

                                containerList.add(containerBuilder.build());
                            }

                            builder.contents(containerList).build().sendTo(src);
                        } else {
                            final Text.Builder builder = Text.builder();
                            builder.append(Text.of(TextColors.RED, "Mods", TextColors.WHITE, " (", sortedContainers.size(), "): "));

                            boolean first = true;
                            for (PluginContainer container : sortedContainers) {
                                if (!first) {
                                    builder.append(SEPARATOR_TEXT);
                                }
                                first = false;

                                builder.append(Text.of(TextColors.GREEN, container.getName()));
                            }

                            src.sendMessage(builder.build());
                        }
                    }
                    return CommandResult.success();
                }).build();
    }

    private static Predicate<? super PluginContainer> getFilteredModsPredicate() {
        return plugin -> !SpongeCommandFactory.CONTAINER_LIST_STATICS.contains(plugin.getId()) && !(plugin instanceof SpongeModPluginContainer);
    }
}
