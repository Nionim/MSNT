package delta.cion.msnt.event.events;

import delta.cion.msnt.event.DeltaEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.event.player.PlayerCommandEvent;

public class ConsoleCommandEvent {

	private static final ComponentSerializer<Component, TextComponent, String>
		SERIALIZER = PlainTextComponentSerializer.plainText();

	private static DeltaEvent<PlayerCommandEvent> buildEvent() {
		return new DeltaEvent<>(PlayerCommandEvent.class, event -> {

		});
	}

}
