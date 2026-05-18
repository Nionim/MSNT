package delta.cion.cherry.test_plugin;

import delta.cion.cherry.api.Plugin;
import delta.cion.cherry.test_plugin.events.PlayerJoinEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Plugin {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public Main() {
		super("Cherry-Test-Plugin");
	}

	@Override
	public void onLoad() {
		sendLog();
		LOGGER.info("Loaded");
	}

	@Override
	public void onEnable() {
		PlayerJoinEvent.register();
		sendLog();
		LOGGER.info("Enabled");
	}

	@Override
	public void onDisable() {
		sendLog();
		LOGGER.info("Disabled");
	}

	private void sendLog() {
		LOGGER.info("{} ({}) is {}", this.getName(), getId(), getStatus());
	}
}
