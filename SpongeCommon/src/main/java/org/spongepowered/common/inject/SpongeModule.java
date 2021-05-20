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
package org.spongepowered.common.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.common.SpongeImpl;
import org.spongepowered.common.inject.provider.config.ConfigDirAnnotation;
import org.spongepowered.common.inject.provider.PathAsFileProvider;

import java.io.File;
import java.nio.file.Path;

public class SpongeModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Path.class).annotatedWith(ConfigDirAnnotation.SHARED).toInstance(SpongeImpl.getPluginConfigDir());
        this.bind(File.class).annotatedWith(ConfigDirAnnotation.SHARED).toProvider(new PathAsFileProvider() {
            @Inject
            void init(@ConfigDir(sharedRoot = true) Provider<Path> path) {
                this.path = path;
            }
        });
    }

}
