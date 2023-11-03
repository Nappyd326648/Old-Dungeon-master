/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.old.olddungeon.items.armor.glyphs;

import com.old.olddungeon.actors.Char;
import com.old.olddungeon.actors.buffs.Charm;
import com.old.olddungeon.actors.buffs.Degrade;
import com.old.olddungeon.actors.buffs.Hex;
import com.old.olddungeon.actors.buffs.MagicalSleep;
import com.old.olddungeon.actors.buffs.Vulnerable;
import com.old.olddungeon.actors.buffs.Weakness;
import com.old.olddungeon.actors.hero.abilities.mage.WarpBeacon;
import com.old.olddungeon.actors.mobs.DM100;
import com.old.olddungeon.actors.mobs.Eye;
import com.old.olddungeon.actors.mobs.Shaman;
import com.old.olddungeon.actors.mobs.Warlock;
import com.old.olddungeon.actors.mobs.YogFist;
import com.old.olddungeon.items.armor.Armor;
import com.old.olddungeon.items.bombs.Bomb;
import com.old.olddungeon.items.rings.RingOfArcana;
import com.old.olddungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.old.olddungeon.items.wands.CursedWand;
import com.old.olddungeon.items.wands.WandOfBlastWave;
import com.old.olddungeon.items.wands.WandOfDisintegration;
import com.old.olddungeon.items.wands.WandOfFireblast;
import com.old.olddungeon.items.wands.WandOfFrost;
import com.old.olddungeon.items.wands.WandOfLightning;
import com.old.olddungeon.items.wands.WandOfLivingEarth;
import com.old.olddungeon.items.wands.WandOfMagicMissile;
import com.old.olddungeon.items.wands.WandOfPrismaticLight;
import com.old.olddungeon.items.wands.WandOfTransfusion;
import com.old.olddungeon.items.wands.WandOfWarding;
import com.old.olddungeon.levels.traps.DisintegrationTrap;
import com.old.olddungeon.levels.traps.GrimTrap;
import com.old.olddungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

import java.util.HashSet;

public class AntiMagic extends Armor.Glyph {

	private static ItemSprite.Glowing TEAL = new ItemSprite.Glowing( 0x88EEFF );
	
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( MagicalSleep.class );
		RESISTS.add( Charm.class );
		RESISTS.add( Weakness.class );
		RESISTS.add( Vulnerable.class );
		RESISTS.add( Hex.class );
		RESISTS.add( Degrade.class );
		
		RESISTS.add( DisintegrationTrap.class );
		RESISTS.add( GrimTrap.class );

		RESISTS.add( Bomb.MagicalBomb.class );
		RESISTS.add( ScrollOfPsionicBlast.class );

		RESISTS.add( CursedWand.class );
		RESISTS.add( WandOfBlastWave.class );
		RESISTS.add( WandOfDisintegration.class );
		RESISTS.add( WandOfFireblast.class );
		RESISTS.add( WandOfFrost.class );
		RESISTS.add( WandOfLightning.class );
		RESISTS.add( WandOfLivingEarth.class );
		RESISTS.add( WandOfMagicMissile.class );
		RESISTS.add( WandOfPrismaticLight.class );
		RESISTS.add( WandOfTransfusion.class );
		RESISTS.add( WandOfWarding.Ward.class );

		RESISTS.add( WarpBeacon.class );
		
		RESISTS.add( DM100.LightningBolt.class );
		RESISTS.add( Shaman.EarthenBolt.class );
		RESISTS.add( Warlock.DarkBolt.class );
		RESISTS.add( Eye.DeathGaze.class );
		RESISTS.add( YogFist.BrightFist.LightBeam.class );
		RESISTS.add( YogFist.DarkFist.DarkBolt.class );
	}
	
	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, see:
		// Hero.damage
		// GhostHero.damage
		// Shadowclone.damage
		// ArmoredStatue.damage
		// PrismaticImage.damage
		return damage;
	}
	
	public static int drRoll( Char ch, int level ){
		return Random.NormalIntRange(
				Math.round(level * RingOfArcana.enchantPowerMultiplier(ch)),
				Math.round((3 + (level*1.5f)) * RingOfArcana.enchantPowerMultiplier(ch)));
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return TEAL;
	}

}