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

package com.old.olddungeon.ui;

import com.old.olddungeon.actors.Char;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.ui.Component;

public class ManaBar extends Component {

	private static final int COLOR_BG	= 0xFFCC0000;
	private static final int COLOR_MP	= 0xFF0CEE00;

	
	private static final int HEIGHT	= 6;
	
	private ColorBlock Bg;

	private ColorBlock Mp;
	
	private float mana;
	private float shield;

	
	@Override
	protected void createChildren() {
		Bg = new ColorBlock( 1, 1, COLOR_BG );
		add( Bg );

		
		Mp = new ColorBlock( 1, 1, COLOR_MP );
		add( Mp );
		
		height = HEIGHT;
	}
	
	@Override
	protected void layout() {
		
		Bg.x = Mp.x = x;
		Bg.y = Mp.y = y;
		
		Bg.size( width, height );
		
		//logic here rounds up to the nearest pixel
		float pixelWidth = width;
		if (camera() != null) pixelWidth *= camera().zoom;
		Mp.size( width * (float)Math.ceil(mana * pixelWidth)/pixelWidth, height );
	}
	
	public void level( float value ) {
		level( value, 0f );
	}

	public void level( float mana, float shield ){
		this.mana = mana;
		this.shield = shield;
		layout();
	}

	public void level(Char c){
		float mana = c.MP;
		float shield = c.shielding();
		float max = Math.max(mana+shield, c.MT);

		level(mana/max, (mana+shield)/max);
	}
}
