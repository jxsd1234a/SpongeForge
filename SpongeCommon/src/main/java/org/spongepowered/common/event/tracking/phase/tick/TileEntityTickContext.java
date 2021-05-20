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
package org.spongepowered.common.event.tracking.phase.tick;

import org.spongepowered.common.bridge.TrackableBridge;
import org.spongepowered.common.event.tracking.IPhaseState;

public class TileEntityTickContext extends LocationBasedTickContext<TileEntityTickContext> {
    public TileEntityTickContext(IPhaseState<TileEntityTickContext> phaseState) {
        super(phaseState);
    }

    @Override
    public TileEntityTickContext source(Object owner) {
        super.source(owner);
        if (owner instanceof TrackableBridge) {
            final TrackableBridge mixinTileentity = (TrackableBridge) owner;
            this.setBlockEvents(mixinTileentity.bridge$allowsBlockEventCreation())
                .setBulkBlockCaptures(mixinTileentity.bridge$allowsBlockBulkCapture())
                .setEntitySpawnEvents(mixinTileentity.bridge$allowsEntityEventCreation())
                .setBulkEntityCaptures(mixinTileentity.bridge$allowsEntityBulkCapture());
        }
        return this;
    }
}
