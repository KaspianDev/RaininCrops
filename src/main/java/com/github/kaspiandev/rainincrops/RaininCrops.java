package com.github.kaspiandev.rainincrops;

import org.bukkit.GameRule;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class RaininCrops extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin unloaded!");
    }

    @EventHandler @SuppressWarnings("ConstantConditions")
    public void onWeather(WeatherChangeEvent event) {
        World world = event.getWorld();
        boolean isClear = world.isClearWeather();
        if (!isClear) {
            int newRTS;
            if (world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED) == null) {
                newRTS = 6;
            } else {
                newRTS = world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED) * 2;
            }
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, newRTS);
        } else {
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, 3);
        }
    }
}
