package net.benjimadness.triad.api.block

import net.benjimadness.triad.TriadMod
import net.minecraft.network.chat.Component
import net.minecraft.util.StringRepresentable
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty

object TriadBlockStateProperties {
    val POWERED: BooleanProperty = BooleanProperty.create("powered")
    val BLADE: EnumProperty<Blades> = EnumProperty.create("blade", Blades::class.java)
    val RUNNING: BooleanProperty = BooleanProperty.create("running")
    val LEVER: EnumProperty<LeverPositions> = EnumProperty.create("lever", LeverPositions::class.java)
    val WATER: BooleanProperty = BooleanProperty.create("water")
    val STEAM: BooleanProperty = BooleanProperty.create("steam")
    val CONTAINS: EnumProperty<Contains> = EnumProperty.create("contains", Contains::class.java)
}

enum class LeverPositions : StringRepresentable {
    NONE, SOUTH, EAST, WEST, BOTTOM, TOP, BOTTOM_ROT, TOP_ROT;
    override fun getSerializedName(): String = name.lowercase()
    override fun toString(): String = serializedName
}

enum class Blades : StringRepresentable {
    NONE, BRONZE, STEEL;
    override fun getSerializedName(): String = name.lowercase()
    override fun toString(): String = serializedName
    private fun getCapitalizedName(): String = serializedName.replaceFirstChar { it.uppercase() }
    fun getComponent(): Component = Component.translatableWithFallback(
        "${TriadMod.MODID}.message.blade.${serializedName}", getCapitalizedName())
}

enum class Contains : StringRepresentable {
    WATER, STEAM, LAVA, NONE;
    override fun getSerializedName(): String = name.lowercase()
    override fun toString(): String = serializedName
}