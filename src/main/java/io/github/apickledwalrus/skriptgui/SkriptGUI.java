package io.github.apickledwalrus.skriptgui;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import io.github.apickledwalrus.skriptgui.gui.GUIManager;

public class SkriptGUI extends JavaPlugin {

	private static SkriptGUI instance;
	private static SkriptAddon addonInstance;

	private static final GUIManager manager = new GUIManager();

	@Override
	public void onEnable() {
		instance = this;

		addonInstance = Skript.registerAddon(this);
		try {
			addonInstance.loadClasses("io.github.apickledwalrus.skriptgui.elements");
			addonInstance.setLanguageFileDirectory("lang");
			SkriptTypes.register();
		} catch (IOException e) {
			getLogger().severe("An error occured while trying to load the addon's elements. The addon will be disabled.");
			getLogger().severe("Printing StackTrace:");
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		}
	}

	public static SkriptGUI getInstance() {
		if (instance == null)
			throw new IllegalStateException("The plugin's instance was requested, but it is null.");
		return instance;
	}

	public static SkriptAddon getAddonInstance() {
		if (addonInstance == null)
			throw new IllegalStateException("The plugin's addon instance was requested, but it is null.");
		return addonInstance;
	}

	public static GUIManager getGUIManager() {
		return manager;
	}

}
