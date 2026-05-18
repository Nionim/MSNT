package delta.cion.cherry.server.command;

import delta.cion.cherry.api.Plugin;
import delta.cion.cherry.api.command.DeltaCommand;
import delta.cion.cherry.server.CherryServer;
import delta.cion.cherry.server.plugin.PluginManager;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReloadCommand extends DeltaCommand<Command> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReloadCommand.class);

	public ReloadCommand() {
		super(new Command("reload"));
		getCommand().addSyntax(this::execute);
	}

	private void execute(CommandSender sender, CommandContext context) {
		sendLog(sender, "Disabling plugins");
		PluginManager.disableAll();
		sendLog(sender, "Enabling plugins");
		PluginManager.enableAll();
		sendLog(sender, "Success plugins");
	}

	private static void sendLog(CommandSender sender, String message) {
		if (!(sender instanceof ConsoleSender))
			sender.sendMessage(message);
		LOGGER.info(message);
	}
}
