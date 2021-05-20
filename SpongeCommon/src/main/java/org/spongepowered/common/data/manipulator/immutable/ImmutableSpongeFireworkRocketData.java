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
package org.spongepowered.common.data.manipulator.immutable;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.ImmutableFireworkRocketData;
import org.spongepowered.api.data.manipulator.mutable.FireworkRocketData;
import org.spongepowered.api.data.value.immutable.ImmutableBoundedValue;
import org.spongepowered.common.data.manipulator.immutable.common.AbstractImmutableIntData;
import org.spongepowered.common.data.manipulator.mutable.SpongeFireworkRocketData;

public class ImmutableSpongeFireworkRocketData extends AbstractImmutableIntData<ImmutableFireworkRocketData, FireworkRocketData>
    implements ImmutableFireworkRocketData {

    public ImmutableSpongeFireworkRocketData(int value) {
        this(value, 0, Integer.MAX_VALUE, 1);
    }

    public ImmutableSpongeFireworkRocketData(int value, int minimum, int maximum, int defaultValue) {
        super(ImmutableFireworkRocketData.class, value, Keys.FIREWORK_FLIGHT_MODIFIER,
            SpongeFireworkRocketData.class, minimum, maximum, defaultValue);
    }

    @Override
    public ImmutableBoundedValue<Integer> flightModifier() {
        return getValueGetter();
    }
}
