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
package org.spongepowered.common.service.permission.base;

import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.service.permission.MemorySubjectData;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.service.permission.SubjectReference;
import org.spongepowered.api.util.Tristate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GlobalMemorySubjectData extends MemorySubjectData {

    /**
     * Creates a new subject data instance, using the provided service to request instances of permission subjects.
     *
     * @param service The service to request subject references from
     */
    public GlobalMemorySubjectData(PermissionService service) {
        super(service);
    }


    @Override
    public Map<Set<Context>, List<SubjectReference>> getAllParents() {
        return ImmutableMap.of(GLOBAL_CONTEXT, getParents(GLOBAL_CONTEXT));
    }

    @Override
    public CompletableFuture<Boolean> setPermission(Set<Context> contexts, String permission, Tristate value) {
        if (!contexts.isEmpty()) {
            return CompletableFuture.completedFuture(false);
        }
        return super.setPermission(contexts, permission, value);
    }

    @Override
    public CompletableFuture<Boolean> clearPermissions(Set<Context> contexts) {
        if (!contexts.isEmpty()) {
            return CompletableFuture.completedFuture(false);
        }
        return super.clearPermissions(contexts);
    }

    @Override
    public CompletableFuture<Boolean> addParent(Set<Context> contexts, SubjectReference parent) {
        if (!contexts.isEmpty()) {
            return CompletableFuture.completedFuture(false);
        }
        return super.addParent(contexts, parent);
    }

    @Override
    public CompletableFuture<Boolean> removeParent(Set<Context> contexts, SubjectReference parent) {
        if (!contexts.isEmpty()) {
            return CompletableFuture.completedFuture(false);
        }
        return super.removeParent(contexts, parent);
    }

    @Override
    public CompletableFuture<Boolean> clearParents(Set<Context> contexts) {
        if (!contexts.isEmpty()) {
            return CompletableFuture.completedFuture(false);
        }
        return super.clearParents(contexts);
    }
}
