package com.rmb938.onesmallfavour;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class OneSmallFavourPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(OneSmallFavourPlugin.class);
		RuneLite.main(args);
	}
}