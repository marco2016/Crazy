package de.st_ddt.crazycaptcha.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.st_ddt.crazycaptcha.CrazyCaptcha;
import de.st_ddt.crazyplugin.commands.CrazyCommandListEditor;
import de.st_ddt.crazyplugin.exceptions.CrazyException;
import de.st_ddt.crazyutil.ChatHelper;
import de.st_ddt.crazyutil.ListFormat;
import de.st_ddt.crazyutil.modules.permissions.PermissionModule;
import de.st_ddt.crazyutil.source.Localized;

public class CrazyCaptchaCommandMainCommands extends CrazyCommandListEditor<CrazyCaptcha, String>
{

	private final ListFormat format = new ListFormat()
	{

		@Override
		@Localized("CRAZYCAPTCHA.COMMAND.COMMANDS.LISTHEAD $CurrentPage$ $MaxPage$ $ChatHeader$ $DateTime$")
		public String headFormat(final CommandSender sender)
		{
			return plugin.getLocale().getLanguageEntry("COMMAND.COMMANDS.LISTHEAD").getLanguageText(sender);
		}

		@Override
		public String listFormat(final CommandSender sender)
		{
			return "$1$\n";
		}

		@Override
		public String entryFormat(final CommandSender sender)
		{
			return "$0$";
		}
	};

	public CrazyCaptchaCommandMainCommands(final CrazyCaptcha plugin)
	{
		super(plugin, true, false, true);
	}

	@Override
	public boolean hasAccessPermission(final CommandSender sender)
	{
		if (sender instanceof Player)
			if (!plugin.isVerified((Player) sender))
				return false;
		return PermissionModule.hasPermission(sender, "crazycaptcha.commands");
	}

	@Override
	public List<String> getCollection()
	{
		return plugin.getCommandWhiteList();
	}

	@Override
	@Localized("CRAZYCAPTCHA.COMMAND.COMMANDS.ADDED $Element$")
	public String addLocale()
	{
		return "COMMAND.COMMANDS.ADDED";
	}

	@Override
	public String addViaIndexLocale()
	{
		return null;
	}

	@Override
	@Localized("CRAZYCAPTCHA.COMMAND.COMMANDS.REMOVED $Element$")
	public String removeLocale()
	{
		return "COMMAND.COMMANDS.REMOVED";
	}

	@Override
	@Localized("CRAZYCAPTCHA.COMMAND.COMMANDS.REMOVED $Element$")
	public String removeViaIndexLocale()
	{
		return "COMMAND.COMMANDS.REMOVED";
	}

	@Override
	public ListFormat listFormat()
	{
		return format;
	}

	@Override
	public String getEntry(final CommandSender sender, final String... args) throws CrazyException
	{
		return ChatHelper.listingString(" ", args);
	}
}
