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

package com.old.olddungeon.levels.traps;

import com.old.olddungeon.Assets;
import com.old.olddungeon.Dungeon;
import com.old.olddungeon.actors.Actor;
import com.old.olddungeon.actors.Char;
import com.old.olddungeon.actors.mobs.Mob;
import com.old.olddungeon.effects.CellEmitter;
import com.old.olddungeon.effects.Speck;
import com.old.olddungeon.items.Heap;
import com.old.olddungeon.items.Honeypot;
import com.old.olddungeon.items.Item;
import com.old.olddungeon.items.scrolls.ScrollOfTeleportation;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class TeleportationTrap extends Trap {

	{
		color = TEAL;
		shape = DOTS;
	}

	@Override
	public void activate() {

		for (int i : PathFinder.NEIGHBOURS9){
			Char ch = Actor.findChar(pos + i);
			if (ch != null){
				if (ScrollOfTeleportation.teleportChar(ch)) {
					if (ch instanceof Mob && ((Mob) ch).state == ((Mob) ch).HUNTING) {
						((Mob) ch).state = ((Mob) ch).WANDERING;
					}
				}
			}
			Heap heap = Dungeon.level.heaps.get(pos + i);
			if (heap != null && heap.type == Heap.Type.HEAP){
				int cell = Dungeon.level.randomRespawnCell( null );

				Item item = heap.pickUp();

				if (cell != -1) {
					Dungeon.level.drop( item, cell );
					if (item instanceof Honeypot.ShatteredPot){
						((Honeypot.ShatteredPot)item).movePot(pos, cell);
					}
					Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
					CellEmitter.get(pos).burst(Speck.factory(Speck.LIGHT), 4);
				}
			}
		}

	}
}
