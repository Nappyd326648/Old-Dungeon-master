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

package com.old.olddungeon.items.journal;

import com.old.olddungeon.Assets;
import com.old.olddungeon.actors.hero.Hero;
import com.old.olddungeon.items.Item;
import com.old.olddungeon.journal.Document;
import com.old.olddungeon.messages.Messages;
import com.old.olddungeon.scenes.GameScene;
import com.old.olddungeon.sprites.ItemSpriteSheet;
import com.old.olddungeon.ui.GameLog;
import com.old.olddungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Guidebook extends Item {

	{
		image = ItemSpriteSheet.MASTERY;
	}

	@Override
	public final boolean doPickUp(Hero hero, int pos) {
		Document.ADVENTURERS_GUIDE.findPage(Document.GUIDE_INTRO);
		Document.ADVENTURERS_GUIDE.findPage(Document.GUIDE_EXAMINING);
		Document.ADVENTURERS_GUIDE.findPage(Document.GUIDE_SURPRISE_ATKS);
		Document.ADVENTURERS_GUIDE.findPage(Document.GUIDE_IDING);
		Document.ADVENTURERS_GUIDE.findPage(Document.GUIDE_FOOD);
		Document.ADVENTURERS_GUIDE.findPage(Document.GUIDE_DIEING);

		GameScene.pickUpJournal(this, pos);
		//we do this here so the pickup message appears before the tutorial text
		GameLog.wipe();
		GLog.i( Messages.capitalize(Messages.get(Hero.class, "you_now_have", name())) );
		GLog.p(Messages.get(GameScene.class, "tutorial_guidebook"));
		GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_INTRO);
		Sample.INSTANCE.play( Assets.Sounds.ITEM );
		hero.spendAndNext( TIME_TO_PICK_UP );
		return true;
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

}
