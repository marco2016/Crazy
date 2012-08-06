package de.st_ddt.crazyutil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.st_ddt.crazyplugin.CrazyPluginInterface;
import de.st_ddt.crazyplugin.data.ParameterData;
import de.st_ddt.crazyutil.locales.CrazyLocale;

public class ChatHelper
{

	private static boolean showChatHeaders;

	private ChatHelper()
	{
	}

	public static boolean isShowingChatHeadersEnabled()
	{
		return showChatHeaders;
	}

	public static void setShowChatHeaders(final boolean showChatHeaders)
	{
		ChatHelper.showChatHeaders = showChatHeaders;
	}

	public static String colorise(final String string)
	{
		return string.replaceAll("\\&0", ChatColor.BLACK.toString()).replaceAll("\\&1", ChatColor.DARK_BLUE.toString()).replaceAll("\\&2", ChatColor.DARK_GREEN.toString()).replaceAll("\\&3", ChatColor.DARK_AQUA.toString()).replaceAll("\\&4", ChatColor.DARK_RED.toString()).replaceAll("\\&5", ChatColor.DARK_PURPLE.toString()).replaceAll("\\&6", ChatColor.GOLD.toString()).replaceAll("\\&7", ChatColor.GRAY.toString()).replaceAll("\\&8", ChatColor.DARK_GRAY.toString()).replaceAll("\\&9", ChatColor.BLUE.toString()).replaceAll("\\&A", ChatColor.GREEN.toString()).replaceAll("\\&B", ChatColor.AQUA.toString()).replaceAll("\\&C", ChatColor.RED.toString()).replaceAll("\\&D", ChatColor.LIGHT_PURPLE.toString()).replaceAll("\\&E", ChatColor.YELLOW.toString()).replaceAll("\\&F", ChatColor.WHITE.toString()).replaceAll("\\&G", ChatColor.MAGIC.toString());
	}

	public static void sendMessage(final CommandSender target, final Object message, final Object... args)
	{
		target.sendMessage(putArgsExtended(target, message, args));
	}

	public static void sendMessage(final CommandSender target, final String chatHeader, final Object message, final Object... args)
	{
		if (target instanceof Player)
			if (!showChatHeaders)
			{
				target.sendMessage(putArgsExtended(target, message, args));
				return;
			}
		target.sendMessage(chatHeader + putArgsExtended(target, message, args));
	}

	public static void sendMessage(final CommandSender[] targets, final Object message, final Object... args)
	{
		for (final CommandSender target : targets)
			sendMessage(target, message, args);
	}

	public static void sendMessage(final CommandSender[] targets, final String chatHeader, final Object message, final Object... args)
	{
		for (final CommandSender target : targets)
			sendMessage(target, message, args);
	}

	public static void sendMessage(final Collection<CommandSender> targets, final Object message, final Object... args)
	{
		for (final CommandSender target : targets)
			sendMessage(target, message, args);
	}

	public static void sendMessage(final Collection<CommandSender> targets, final String chatHeader, final Object message, final Object... args)
	{
		for (final CommandSender target : targets)
			sendMessage(target, message, args);
	}

	public static <E> void sendListMessage(final CommandSender target, final CrazyPluginInterface plugin, final String headLocale, final String seperator, final String entry, final String emptyPage, final int amount, final int page, final List<? extends E> datas, final EntryDataGetter<E> getter)
	{
		sendListMessage(target, plugin.getChatHeader(), plugin.getLocale().getLanguageEntry(headLocale), seperator == null ? null : plugin.getLocale().getLanguageEntry(seperator), entry == null ? null : plugin.getLocale().getLanguageEntry(entry), emptyPage == null ? null : plugin.getLocale().getLanguageEntry(emptyPage), amount, page, datas, getter);
	}

	public static <E> void sendListMessage(final CommandSender target, final String chatHeader, final String headLocale, final String seperator, final String entry, final String emptyPage, final int amount, final int page, final List<? extends E> datas, final EntryDataGetter<E> getter)
	{
		sendListMessage(target, chatHeader, CrazyLocale.getLocaleHead().getLanguageEntry(headLocale), seperator == null ? null : CrazyLocale.getLocaleHead().getLanguageEntry(seperator), entry == null ? null : CrazyLocale.getLocaleHead().getLanguageEntry(entry), emptyPage == null ? null : CrazyLocale.getLocaleHead().getLanguageEntry(emptyPage), amount, page, datas, getter);
	}

	public static <E> void sendListMessage(final CommandSender target, String chatHeader, final CrazyLocale headLocale, CrazyLocale seperator, CrazyLocale entry, CrazyLocale emptyPage, int amount, int page, final List<? extends E> datas, final EntryDataGetter<E> getter)
	{
		if (chatHeader == null)
			chatHeader = "";
		final int lastIndex = datas.size();
		if (page == Integer.MIN_VALUE)
			amount = lastIndex;
		else if (amount == 0)
			amount = 10;
		else if (amount < 0)
			amount = lastIndex;
		page = Math.max(1, page);
		if (seperator == null)
			seperator = CrazyLocale.getLocaleHead().getLanguageEntry("CRAZYPLUGIN.LIST.SEPERATOR");
		if (emptyPage == null)
			emptyPage = CrazyLocale.getLocaleHead().getLanguageEntry("CRAZYPLUGIN.LIST.EMPTYPAGE");
		if (entry == null)
			entry = CrazyLocale.getLocaleHead().getLanguageEntry("CRAZYPLUGIN.LIST.ENTRY");
		CrazyPage.storePagedData(target, chatHeader, headLocale, seperator, entry, emptyPage, amount, page, datas, getter).show(target);
	}

	public static String putArgs(final String message, final Object... args)
	{
		String res = message;
		final int length = args.length;
		for (int i = 0; i < length; i++)
			res = res.replaceAll("\\$" + i + "\\$", args[i].toString());
		return res;
	}

	public static String putArgsPara(final String message, final ParameterData data)
	{
		String res = message;
		final int length = data.getParameterCount();
		for (int i = 0; i < length; i++)
			res = res.replaceAll("\\$" + i + "\\$", data.getParameter(i));
		return res;
	}

	public static String putArgsExtended(final CommandSender target, final Object message, final Object... args)
	{
		String res = message.toString();
		if (message instanceof CrazyLocale)
			res = ((CrazyLocale) message).getLanguageText(target);
		final int length = args.length;
		for (int i = 0; i < length; i++)
			res = res.replaceAll("\\$" + i + "\\$", (args[i] instanceof CrazyLocale ? ((CrazyLocale) args[i]).getLanguageText(target) : args[i].toString()));
		return res;
	}

	public static <S> S[] cutArray(final S[] array, final int anz)
	{
		if (anz < 0)
			return Arrays.copyOf(array, 0);
		else
			return Arrays.copyOfRange(array, 0, anz);
	}

	public static <S> S[] shiftArray(final S[] array, final int anz)
	{
		if (anz >= array.length)
			return Arrays.copyOf(array, 0);
		else
			return Arrays.copyOfRange(array, anz, array.length);
	}

	public static <S> String listingString(final S[] strings)
	{
		return listingString(", ", strings);
	}

	public static <S> String listingString(final String seperator, final S... strings)
	{
		final int length = strings.length;
		if (length == 0)
			return "";
		String res = strings[0].toString();
		for (int i = 1; i < length; i++)
		{
			res = res + seperator + strings[i].toString();
		}
		return res;
	}

	public static <S> String listingString(final Collection<S> strings)
	{
		return listingString(", ", strings);
	}

	public static <S> String listingString(final String seperator, final Collection<S> strings)
	{
		if (strings.size() == 0)
			return "";
		final Iterator<S> list = strings.iterator();
		String res = list.next().toString();
		while (list.hasNext())
			res = res + seperator + list.next().toString();
		return res;
	}

	public static String dateToString(final Date date)
	{
		return CrazyPluginInterface.DateFormat.format(date);
	}

	public static String[] toList(final ParameterData data)
	{
		final int count = data.getParameterCount();
		final String[] res = new String[count];
		for (int i = 0; i < count; i++)
			res[i] = data.getParameter(i);
		return res;
	}
}