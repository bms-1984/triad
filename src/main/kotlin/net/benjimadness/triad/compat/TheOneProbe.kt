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

package net.benjimadness.triad.compat

import mcjty.theoneprobe.api.*
import net.benjimadness.triad.TriadMod
import net.benjimadness.triad.block.GrinderBlock
import net.benjimadness.triad.api.block.TriadBlockStateProperties
import net.benjimadness.triad.api.util.ComponentUtil
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.fml.InterModComms
import net.neoforged.fml.ModList
import java.util.function.Function

object TheOneProbe {
    fun register() {
        if (!ModList.get().isLoaded("theoneprobe"))
            return
        InterModComms.sendTo("theoneprobe", "getTheOneProbe", ::GetTheOneProbe)

    }

    class GetTheOneProbe : Function<ITheOneProbe, Unit> {
        override fun apply(probe: ITheOneProbe) {
            TriadMod.LOGGER.info("Singing my support for The One Probe")
            probe.registerProvider(object : IProbeInfoProvider {
                override fun getID(): ResourceLocation = ResourceLocation(TriadMod.MODID,"the_one_probe_provider")
                override fun addProbeInfo(mode: ProbeMode?, info: IProbeInfo?, player: Player?, level: Level?,
                    state: BlockState?, hitData: IProbeHitData?
                ) {
                    if (state != null && state.block is GrinderBlock && info != null) {
                        info.horizontal().text(ComponentUtil.combine(
                            Component.translatable("${TriadMod.MODID}.message.blade"),
                            Component.literal(": "),
                            state.getValue(TriadBlockStateProperties.BLADE).getComponent()))
                    }
                }
            })
        }
    }
}