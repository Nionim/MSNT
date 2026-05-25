package delta.cion.cherry.test_plugin;

import delta.cion.cherry.api.Plugin;
import delta.cion.cherry.api.command.DeltaCommand;
import delta.cion.cherry.api.event.DeltaEvent;
import delta.cion.cherry.test_plugin.command.GetCommand;
import delta.cion.cherry.test_plugin.command.TestUnit;
import delta.cion.cherry.test_plugin.event.PlayerBorderEvent;
import delta.cion.cherry.test_plugin.event.PlayerConnectionEvent;
import delta.cion.cherry.test_plugin.event.PlayerDamageEvent;
import delta.cion.cherry.test_plugin.pvp.DamageBasics;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerMoveEvent;

public class Main extends Plugin {

	private static final Pos SPAWN_POSITION = new Pos(0.5, 50.0, 0.5);
	private static final Pos MOB_POSITION = new Pos(3.5, 50.0, 0.5);

	private static DeltaEvent<PlayerMoveEvent> WORLD_BORDER;

	private static DeltaEvent<AsyncPlayerConfigurationEvent> CONNECTION_EVENT;
	private static DeltaEvent<PlayerDisconnectEvent> DISCONNECT_EVENT;

	private static DeltaEvent<EntityDamageEvent> PLAYER_DAMAGE_EVENT;
	private static DeltaEvent<EntityAttackEvent> ENTITY_ATTACK_EVENT;

	private static final DeltaCommand TEST_UNIT_SPAWN_COMMAND = new TestUnit();
	private static final DeltaCommand GET_COMMAND = new GetCommand();

	public Main() {
		super("Cherry-Test-Plugin");
	}

	@Override
	public void onEnable() {
		DamageBasics.registerAll();
		WORLD_BORDER = PlayerBorderEvent.playerMoveEvent();
		WORLD_BORDER.register();

		PlayerConnectionEvent.init();
		CONNECTION_EVENT = PlayerConnectionEvent.connectPlayer();
		DISCONNECT_EVENT = PlayerConnectionEvent.exitPlayer();
		PLAYER_DAMAGE_EVENT = PlayerDamageEvent.playerDamageEvent();
		ENTITY_ATTACK_EVENT = PlayerDamageEvent.entityAttackEvent();

		CONNECTION_EVENT.register();
		DISCONNECT_EVENT.register();
		PLAYER_DAMAGE_EVENT.register();
		ENTITY_ATTACK_EVENT.register();

		TEST_UNIT_SPAWN_COMMAND.register();
		GET_COMMAND.register();
	}

	@Override
	public void onDisable() {
		// Events
		WORLD_BORDER.unregister();
		CONNECTION_EVENT.unregister();
		DISCONNECT_EVENT.unregister();
		PLAYER_DAMAGE_EVENT.unregister();
		ENTITY_ATTACK_EVENT.unregister();
		// Commands
		TEST_UNIT_SPAWN_COMMAND.unregister();
		GET_COMMAND.unregister();
	}

	public static Pos getSpawnPosition() {
		return SPAWN_POSITION;
	}

	public static Pos getMobPosition() {
		return MOB_POSITION;
	}
}
