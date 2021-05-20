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
package org.spongepowered.common.mixin.core.entity;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * Specialized {@link Accessor} mixin
 */
@Mixin(EntityAreaEffectCloud.class)
public interface EntityAreaEffectCloudAccessor {


    @Accessor("RADIUS")
    static DataParameter<Float> accessor$getRadiusParameter() {
        throw new IllegalStateException("Untransformed Accessor!");
    }

    @Accessor("COLOR")
    static DataParameter<Integer> accessor$getColorParameter() {
        throw new IllegalStateException("Untransformed Accessor!");
    }

    @Accessor("IGNORE_RADIUS")
    static DataParameter<Boolean> accessor$getIgnoreRadiusParameter() {
        throw new IllegalStateException("Untransformed Accessor!");
    }

    @Accessor("PARTICLE")
    static DataParameter<Integer> accessor$getParticleParameter() {
        throw new IllegalStateException("Untransformed Accessor!");
    }

    @Accessor("PARTICLE_PARAM_1")
    static DataParameter<Integer> accessor$getParticleParam1Parameter() {
        throw new IllegalStateException("Untransformed Accessor!");
    }

    @Accessor("PARTICLE_PARAM_2")
    static DataParameter<Integer> accessor$getParticleParam2Parameter() {
        throw new IllegalStateException("Untransformed Accessor!");
    }



    @Accessor
    void setDurationOnUse(int radiusOnUse);

    @Accessor
    int getDurationOnUse();

    @Accessor("effects")
    List<PotionEffect> getPotionEffects();

    @Accessor("effects")
    void setPotionEffects(List<PotionEffect> potionEffects);

    @Accessor
    int getReapplicationDelay();

    @Accessor
    void setReapplicationDelay(int delay);

    @Accessor
    float getRadiusOnUse();

    @Accessor
    float getRadiusPerTick();

    @Accessor
    int getWaitTime();
}
