package delta.cion.msnt.motd;

import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.ping.ResponseData;
import delta.cion.msnt.event.DeltaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Consumer;

public class MOTDHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MOTDHandler.class);

	private static final Consumer<ServerListPingEvent> DEFAULT_HANDLER = event -> {
		ResponseData data = new ServerMOTD()
			.setMOTDVersion("MSNT server 1.21.4")
			.setMOTDDescription("MSNT Server")
			.setMOTDMaxPlayer(1663)
			.setMOTDOnline(-1)
			.get();
		event.setResponseData(data);

		LOGGER.debug("Server pinged by {}",
			Objects.requireNonNull(event.getConnection()).getRemoteAddress());
	};

	private static DeltaEvent<ServerListPingEvent> serverListPingEvent;

	public static void registerCustomMOTD(Consumer<ServerListPingEvent> handler) {
		if (serverListPingEvent != null) serverListPingEvent.unregister();
		if (handler == null) {

			registerVanillaMOTD();
		}
		serverListPingEvent = new DeltaEvent<>(ServerListPingEvent.class, handler);
		serverListPingEvent.register();
	}

	public static void registerVanillaMOTD() {
		registerCustomMOTD(DEFAULT_HANDLER);
	}
}
