package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.utils.DMZClientConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncConfig {
    private final String config;
    private final double stat;

    public PacketSyncConfig(String config, double stat) {
        this.config = config;
        this.stat = stat;
    }

    public PacketSyncConfig(FriendlyByteBuf buf) {
        this.config = buf.readUtf();
        this.stat = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(config);
        buf.writeDouble(stat);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Guardar los valores en el cliente
            switch (config){
                case "maxattributes":
                    DMZClientConfig.setMaxStats((int) stat);
                    break;
                case "zpoints_cost":
                    DMZClientConfig.setMultiplierZPoints(stat);
                    break;
                case "majin_multiplier":
                    DMZClientConfig.setMajin_multi(stat);
                    break;
                case "tree_might_multiplier":
                    DMZClientConfig.setTree_might_multi(stat);
                    break;

                case "ini_str_human_warrior":
                    DMZClientConfig.setInit_StrStat("human", "warrior",(int) stat);
                    break;
                case "ini_str_saiyan_warrior":
                    DMZClientConfig.setInit_StrStat("saiyan", "warrior",(int) stat);
                    break;
                case "ini_str_namek_warrior":
                    DMZClientConfig.setInit_StrStat("namek", "warrior",(int) stat);
                    break;
                case "ini_str_bio_warrior":
                    DMZClientConfig.setInit_StrStat("bio", "warrior",(int) stat);
                    break;
                case "ini_str_cold_warrior":
                    DMZClientConfig.setInit_StrStat("cold", "warrior",(int) stat);
                    break;
                case "ini_str_majin_warrior":
                    DMZClientConfig.setInit_StrStat("majin", "warrior",(int) stat);
                    break;

                case "ini_def_human_warrior":
                    DMZClientConfig.setInit_DefStat("human", "warrior",(int) stat);
                    break;
                case "ini_def_saiyan_warrior":
                    DMZClientConfig.setInit_DefStat("saiyan", "warrior",(int) stat);
                    break;
                case "ini_def_namek_warrior":
                    DMZClientConfig.setInit_DefStat("namek", "warrior",(int) stat);
                    break;
                case "ini_def_bio_warrior":
                    DMZClientConfig.setInit_DefStat("bio", "warrior",(int) stat);
                    break;
                case "ini_def_cold_warrior":
                    DMZClientConfig.setInit_DefStat("cold", "warrior",(int) stat);
                    break;
                case "ini_def_majin_warrior":
                    DMZClientConfig.setInit_DefStat("majin", "warrior",(int) stat);
                    break;

                case "ini_con_human_warrior":
                    DMZClientConfig.setInit_ConStat("human", "warrior",(int) stat);
                    break;
                case "ini_con_saiyan_warrior":
                    DMZClientConfig.setInit_ConStat("saiyan", "warrior",(int) stat);
                    break;
                case "ini_con_namek_warrior":
                    DMZClientConfig.setInit_ConStat("namek", "warrior",(int) stat);
                    break;
                case "ini_con_bio_warrior":
                    DMZClientConfig.setInit_ConStat("bio", "warrior",(int) stat);
                    break;
                case "ini_con_cold_warrior":
                    DMZClientConfig.setInit_ConStat("cold", "warrior",(int) stat);
                    break;
                case "ini_con_majin_warrior":
                    DMZClientConfig.setInit_ConStat("majin", "warrior",(int) stat);
                    break;

                case "ini_pwr_human_warrior":
                    DMZClientConfig.setInit_PWRStat("human","spiritualist",(int) stat);
                    break;
                case "ini_pwr_saiyan_warrior":
                    DMZClientConfig.setInit_PWRStat("saiyan","warrior",(int) stat);
                    break;
                case "ini_pwr_namek_warrior":
                    DMZClientConfig.setInit_PWRStat("namek","warrior",(int) stat);
                    break;
                case "ini_pwr_bio_warrior":
                    DMZClientConfig.setInit_PWRStat("bio","warrior",(int) stat);
                    break;
                case "ini_pwr_cold_warrior":
                    DMZClientConfig.setInit_PWRStat("cold","warrior",(int) stat);
                    break;
                case "ini_pwr_majin_warrior":
                    DMZClientConfig.setInit_PWRStat("majin","warrior",(int) stat);
                    break;

                case "ini_ene_human_warrior":
                    DMZClientConfig.setInit_ENEStat("human","warrior",(int) stat);
                    break;
                case "ini_ene_saiyan_warrior":
                    DMZClientConfig.setInit_ENEStat("saiyan","warrior",(int) stat);
                    break;
                case "ini_ene_namek_warrior":
                    DMZClientConfig.setInit_ENEStat("namek","warrior",(int) stat);
                    break;
                case "ini_ene_bio_warrior":
                    DMZClientConfig.setInit_ENEStat("bio","warrior",(int) stat);
                    break;
                case "ini_ene_cold_warrior":
                    DMZClientConfig.setInit_ENEStat("cold","warrior",(int) stat);
                    break;
                case "ini_ene_majin_warrior":
                    DMZClientConfig.setInit_ENEStat("majin","warrior",(int) stat);
                    break;
                    //SPIRITUALIST
                case "ini_str_human_spiritualist":
                    DMZClientConfig.setInit_StrStat("human", "spiritualist",(int) stat);
                    break;
                case "ini_str_saiyan_spiritualist":
                    DMZClientConfig.setInit_StrStat("saiyan", "spiritualist",(int) stat);
                    break;
                case "ini_str_namek_spiritualist":
                    DMZClientConfig.setInit_StrStat("namek", "spiritualist",(int) stat);
                    break;
                case "ini_str_bio_spiritualist":
                    DMZClientConfig.setInit_StrStat("bio", "spiritualist",(int) stat);
                    break;
                case "ini_str_cold_spiritualist":
                    DMZClientConfig.setInit_StrStat("cold", "spiritualist",(int) stat);
                    break;
                case "ini_str_majin_spiritualist":
                    DMZClientConfig.setInit_StrStat("majin", "spiritualist",(int) stat);
                    break;

                case "ini_def_human_spiritualist":
                    DMZClientConfig.setInit_DefStat("human", "spiritualist",(int) stat);
                    break;
                case "ini_def_saiyan_spiritualist":
                    DMZClientConfig.setInit_DefStat("saiyan", "spiritualist",(int) stat);
                    break;
                case "ini_def_namek_spiritualist":
                    DMZClientConfig.setInit_DefStat("namek", "spiritualist",(int) stat);
                    break;
                case "ini_def_bio_spiritualist":
                    DMZClientConfig.setInit_DefStat("bio", "spiritualist",(int) stat);
                    break;
                case "ini_def_cold_spiritualist":
                    DMZClientConfig.setInit_DefStat("cold", "spiritualist",(int) stat);
                    break;
                case "ini_def_majin_spiritualist":
                    DMZClientConfig.setInit_DefStat("majin", "spiritualist",(int) stat);
                    break;

                case "ini_con_human_spiritualist":
                    DMZClientConfig.setInit_ConStat("human", "spiritualist",(int) stat);
                    break;
                case "ini_con_saiyan_spiritualist":
                    DMZClientConfig.setInit_ConStat("saiyan", "spiritualist",(int) stat);
                    break;
                case "ini_con_namek_spiritualist":
                    DMZClientConfig.setInit_ConStat("namek", "spiritualist",(int) stat);
                    break;
                case "ini_con_bio_spiritualist":
                    DMZClientConfig.setInit_ConStat("bio", "spiritualist",(int) stat);
                    break;
                case "ini_con_cold_spiritualist":
                    DMZClientConfig.setInit_ConStat("cold", "spiritualist",(int) stat);
                    break;
                case "ini_con_majin_spiritualist":
                    DMZClientConfig.setInit_ConStat("majin", "spiritualist",(int) stat);
                    break;

                case "ini_pwr_human_spiritualist":
                    DMZClientConfig.setInit_PWRStat("human","spiritualist",(int) stat);
                    break;
                case "ini_pwr_saiyan_spiritualist":
                    DMZClientConfig.setInit_PWRStat("saiyan","spiritualist",(int) stat);
                    break;
                case "ini_pwr_namek_spiritualist":
                    DMZClientConfig.setInit_PWRStat("namek","spiritualist",(int) stat);
                    break;
                case "ini_pwr_bio_spiritualist":
                    DMZClientConfig.setInit_PWRStat("bio","spiritualist",(int) stat);
                    break;
                case "ini_pwr_cold_spiritualist":
                    DMZClientConfig.setInit_PWRStat("cold","spiritualist",(int) stat);
                    break;
                case "ini_pwr_majin_spiritualist":
                    DMZClientConfig.setInit_PWRStat("majin","spiritualist",(int) stat);
                    break;

                case "ini_ene_human_spiritualist":
                    DMZClientConfig.setInit_ENEStat("human","spiritualist",(int) stat);
                    break;
                case "ini_ene_saiyan_spiritualist":
                    DMZClientConfig.setInit_ENEStat("saiyan","spiritualist",(int) stat);
                    break;
                case "ini_ene_namek_spiritualist":
                    DMZClientConfig.setInit_ENEStat("namek","spiritualist",(int) stat);
                    break;
                case "ini_ene_bio_spiritualist":
                    DMZClientConfig.setInit_ENEStat("bio","spiritualist",(int) stat);
                    break;
                case "ini_ene_cold_spiritualist":
                    DMZClientConfig.setInit_ENEStat("cold","spiritualist",(int) stat);
                    break;
                case "ini_ene_majin_spiritualist":
                    DMZClientConfig.setInit_ENEStat("majin","spiritualist",(int) stat);
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}