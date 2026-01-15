package com.kssjw.railwayswitchcontroller.manager;

import com.kssjw.railwayswitchcontroller.util.ModIdUtil;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class NetworkManager {
    
    public record SwitchRailPayload() implements CustomPayload {

        public static final Id<SwitchRailPayload> ID = new Id<>(Identifier.of(ModIdUtil.MOD_ID, "switch_rail"));

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }

        public static final PacketCodec<PacketByteBuf, SwitchRailPayload> CODEC = PacketCodec.unit(new SwitchRailPayload());
    }
}