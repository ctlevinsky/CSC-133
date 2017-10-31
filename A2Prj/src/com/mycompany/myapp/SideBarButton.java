package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Image;

public class SideBarButton extends Button {

	public SideBarButton() {
		// TODO Auto-generated constructor stub
	}

	public SideBarButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public SideBarButton(Command cmd) {
		super(cmd);
		this.getAllStyles().setBgColor(ColorUtil.CYAN);
		this.getAllStyles().setFgColor(ColorUtil.BLUE);
	}

	public SideBarButton(Image icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public SideBarButton(String text, Image icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	

}
