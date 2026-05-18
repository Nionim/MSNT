package delta.cion.cherry.api;

import net.minestom.server.event.GlobalEventHandler;

import java.util.UUID;

public abstract class Plugin {

	private final String name;
	private final UUID id;
	private Status status;

	private static GlobalEventHandler GLOBAL_EVENT_HANDLER;

	public Plugin(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.status = Status.DISABLED;
	}

	public static void setGlobalEventHandler(GlobalEventHandler handler) {
		if (GLOBAL_EVENT_HANDLER != null) return;
		GLOBAL_EVENT_HANDLER = handler;
	}

	public String getName() {
		return name;
	}

	public UUID getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public void onLoad() {}

	public abstract void onEnable();

	public abstract void onDisable();

	public void updateStatus(Status newStatus) {
		this.status = newStatus;
	}

	public static GlobalEventHandler getGlobalEventHandler() {
		return GLOBAL_EVENT_HANDLER;
	}

	public enum Status {
		ENABLED, DISABLED, OUTDATED
	}
}
