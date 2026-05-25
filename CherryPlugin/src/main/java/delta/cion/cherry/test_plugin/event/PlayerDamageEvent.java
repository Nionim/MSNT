package delta.cion.cherry.test_plugin.event;

import delta.cion.cherry.api.event.DeltaEvent;
import delta.cion.cherry.test_plugin.pvp.CountDamage;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.damage.Damage;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.registry.DynamicRegistry;

public class PlayerDamageEvent {

	public static DeltaEvent<EntityDamageEvent> playerDamageEvent() {
		return new DeltaEvent<>(EntityDamageEvent.class, event -> {
			Entity entity = event.getDamage().getAttacker();
			if (!(entity instanceof Player player)) return;

			Damage damage = event.getDamage();
			player.sendMessage("Damage count: ["+damage.getAmount()+"].");
			player.sendMessage("Damage type: ["+damage.getType()+"].");
		});
	}

	public static DeltaEvent<EntityAttackEvent> entityAttackEvent() {
		return new DeltaEvent<>(EntityAttackEvent.class, event -> {
			Entity target = event.getTarget();
			if (!(target instanceof LivingEntity livingTarget)) return;

			DynamicRegistry.Key<DamageType> damageType = null;
			Entity attacker = event.getEntity();
			float damageCount = 0f;

			if (attacker instanceof Player player) {
				damageType = DamageType.PLAYER_ATTACK;
				assert player.getItemUseHand() != null;
				ItemStack item = player.getItemInHand(player.getItemUseHand());
				damageCount = CountDamage.countDamage(item, attacker);
			} else if (!(attacker instanceof LivingEntity livingAttacker)) {
				damageType = DamageType.MOB_ATTACK;
				damageCount = 1f;
			}
			else {
				damageType = DamageType.MOB_ATTACK;
				damageCount = 1f;
			}

			Damage damage = new Damage(damageType, target, attacker, target.getPosition(), damageCount);
			livingTarget.damage(damage);
		});
	}
}
