package de.st_ddt.crazyplugin.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class CrazyPlayerPreRemoveEvent extends CrazyEvent implements Cancellable
{

	private static final HandlerList handlers = new HandlerList();
	protected final String player;
	protected boolean cancelled = false;

	public CrazyPlayerPreRemoveEvent(final OfflinePlayer player)
	{
		super();
		this.player = player.getName();
	}

	public CrazyPlayerPreRemoveEvent(final String player)
	{
		super();
		this.player = player;
	}

	public String getPlayer()
	{
		return player;
	}

	@Override
	public boolean isCancelled()
	{
		return cancelled;
	}

	@Override
	public void setCancelled(final boolean cancelled)
	{
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}
}
