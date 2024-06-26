/*
 *     Triad, a tech mod for Minecraft
 *     Copyright (C) 2024  Ben M. Sutter
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.benjimadness.triad.api.item

import net.minecraft.util.RandomSource
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

open class ReusableItem(properties: Properties) : Item(properties) {
    private val rand: RandomSource = RandomSource.create()
    override fun hasCraftingRemainingItem(stack: ItemStack): Boolean {
        var ret = true
        stack.copy().hurtAndBreak(1, rand, null) {
            ret = true
        }
        return ret
    }
    override fun getCraftingRemainingItem(stack: ItemStack): ItemStack {
        var ret = stack.copy()
        ret.hurtAndBreak(1, rand, null) {
            ret = ItemStack.EMPTY
        }
        return ret
    }
}