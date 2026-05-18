package delta.cion.cherry.api.online;

import java.time.LocalDateTime;
import java.util.UUID;

public record PlayerInfo (String name, UUID uuid, LocalDateTime date) { }
