package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.DMZPermanentEffectsSyncS2C;
import com.yuseix.dragonminez.network.S2C.DMZSkillsS2C;
import com.yuseix.dragonminez.network.S2C.DMZTempEffectsS2C;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;

public class DMZStatsAttributes {

    private Map<String, DMZSkill> DMZSkills = new HashMap<>();
    private Map<String, Boolean> DMZPermanentEffects = new HashMap<>();
    private Map<String, Integer> DMZTemporalEffects = new HashMap<>();

    private DMZDatos dmzdatos = new DMZDatos();

    private int races;
    private int hairID, bodytype, eyesType;
    private int strength = 5;
    private int defense = 5;
    private int constitution = 5, curStam;

    private int dmzRelease = 5;
    private int dmzState = 0;
    private int dmzSenzuDaily = 0;

    private int zpoints;
    private int KiPower = 5;

    private int energy = 5, currentEnergy;
    private int dmzAlignment = 100;

    private int bodyColor, bodyColor2, bodyColor3, eye1Color, eye2Color, hairColor = 921617, auraColor = 8388607;

    private String gender = "Male";
    private String dmzClass = "Warrior";
    private String dmzKiWeapon = "sword";

    private boolean AcceptCharacter = false, isauraOn = false, isDescendkeyon = false, isTurbonOn = false, compactMenu = false;



    private final Player player;

    public DMZStatsAttributes(Player player) {
        this.player = player;
    }
    public boolean isTurbonOn() {
        return isTurbonOn;
    }

    public void setTurboOn(boolean auraOn) {
        isTurbonOn = auraOn;
        DMZStatsCapabilities.syncStats(player);
    }
    public boolean isAuraOn() {
        return isauraOn;
    }

    public void setAuraOn(boolean auraOn) {
        isauraOn = auraOn;
        DMZStatsCapabilities.syncStats(player);
    }
    public boolean isDescendKeyOn() {
        return isDescendkeyon;
    }

    public void setDescendKey(boolean descendKey) {
        isDescendkeyon = descendKey;
        DMZStatsCapabilities.syncStats(player);

    }


    public int getDmzSenzuDaily() {
        return dmzSenzuDaily;
    }

    public void setDmzSenzuDaily(int dmzSenzuDaily) {
        this.dmzSenzuDaily = dmzSenzuDaily;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getDmzRelease() {
        return dmzRelease;
    }
    public void setDmzRelease(int dmzRelease) {
        this.dmzRelease = dmzRelease;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getDmzState() {
        return dmzState;
    }

    public void setDmzState(int dmzState) {
        this.dmzState = dmzState;
        DMZStatsCapabilities.syncStats(player);
    }

    public String getDmzClass() {
        return dmzClass;
    }

    public int getDmzAlignment() {
        return dmzAlignment;
    }

    public int getZpoints() {
        return zpoints;
    }

    public void setZpoints(int zpoints) {
        this.zpoints = zpoints;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(int bodyColor) {
        this.bodyColor = bodyColor;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getBodyColor2() {
        return bodyColor2;
    }

    public void setBodyColor2(int bodyColor2) {
        this.bodyColor2 = bodyColor2;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getBodyColor3() {
        return bodyColor3;
    }

    public void setBodyColor3(int bodyColor3) {
        this.bodyColor3 = bodyColor3;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getEye1Color() {
        return eye1Color;

    }

    public void setEye1Color(int eye1Color) {
        this.eye1Color = eye1Color;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getEye2Color() {
        return eye2Color;
    }

    public void setEye2Color(int eye2Color) {
        this.eye2Color = eye2Color;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getAuraColor() {
        return auraColor;
    }

    public void setAuraColor(int auraColor) {
        this.auraColor = auraColor;
        DMZStatsCapabilities.syncStats(player);
    }

    public void addStrength(int points) {

        if (strength <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            strength += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addDefense(int points) {

        if (defense <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            defense += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addCon(int points) {

        if (constitution <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            constitution += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }


    public void addKipwr(int points) {

        if (KiPower <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            KiPower += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addEnergy(int points) {

        if (energy <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            energy += points;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void addZpoints(int points) {

        zpoints += points;
        DMZStatsCapabilities.syncStats(player);

    }

    public void removeZpoints(int points) {

        if (this.zpoints >= points) {
            this.zpoints -= points;
        } else {
            this.zpoints = 0;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void removeStrenght(int points) {

        this.strength -= points;

        if (this.strength < 1) {
            this.strength = 1;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public void removeDefense(int points) {

        this.defense -= points;

        if (this.defense < 1) {
            this.defense = 1;
        }
        DMZStatsCapabilities.syncStats(player);
    }

    public void removeConstitution(int points) {

        this.constitution -= points;

        if (this.constitution < 1) {
            this.constitution = 1;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public void removeKiPower(int points) {

        this.KiPower -= points;

        if (this.KiPower < 1) {
            this.KiPower = 1;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void removeEnergy(int points) {

        this.energy -= points;

        if (this.energy < 1) {
            this.energy = 1;
        }

        DMZStatsCapabilities.syncStats(player);

    }


    public int getRace() {
        return races;
    }

    public void setRace(int races) {
        this.races = Math.min(races, 6);
        DMZStatsCapabilities.syncStats(player);
    }

    public int getHairID() {
        return hairID;
    }

    public void setHairID(int hairID) {
        this.hairID = hairID;
        DMZStatsCapabilities.syncStats(player);

    }

    public int getBodytype() {
        return bodytype;
    }

    public void setBodytype(int bodytype) {
        this.bodytype = bodytype;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getEyesType() {
        return eyesType;
    }

    public void setEyesType(int eyesType) {
        this.eyesType = eyesType;
        DMZStatsCapabilities.syncStats(player);

    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {

        this.strength = strength;

        if (this.strength >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.strength = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.strength = strength;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getDefense() {

        return defense;
    }

    public void setDefense(int defense) {

        this.defense = defense;

        if (this.defense >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.defense = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.defense = defense;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {

        this.constitution = constitution;

        if (this.constitution >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.constitution = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.constitution = constitution;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getKiPower() {
        return KiPower;
    }

    public void setKiPower(int kiPower) {

        this.KiPower = kiPower;

        if (this.KiPower >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            KiPower = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.KiPower = kiPower;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {

        this.energy = energy;

        if (this.energy >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.energy = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.energy = energy;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {

        var maxEne = 0;

        maxEne = dmzdatos.calcularENE(races, energy, dmzClass);

        if(currentEnergy >= maxEne){
            this.currentEnergy = maxEne;
        } else {
            this.currentEnergy = currentEnergy;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void removeCurEnergy(int currentEnergy) {
        this.currentEnergy -= currentEnergy;

        if (this.currentEnergy < 0) {
            this.currentEnergy = 0;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void addCurEnergy(int currentEnergy) {

        var maxEne = 0;

        maxEne = dmzdatos.calcularENE(races, energy, dmzClass);

        if(currentEnergy >= maxEne){
            this.currentEnergy = maxEne;
        } else {
            this.currentEnergy += currentEnergy;

            if(this.currentEnergy > maxEne){
                this.currentEnergy = maxEne;
            }
        }


        DMZStatsCapabilities.syncStats(player);
    }

    public int getMaxHealth() {
        return dmzdatos.calcularCON(races, constitution, 20, dmzClass);
    }

    public int getMaxEnergy() {
        return dmzdatos.calcularENE(races, energy, dmzClass);
    }

    public int getCurStam() {

        return curStam;
    }

    public void setCurStam(int curStam) {

        var maxStam = 0;
        var maxVIDA = 0;


        maxVIDA = dmzdatos.calcularCON(races, constitution, 20, dmzClass);
        maxStam = dmzdatos.calcularSTM(races, maxVIDA);

        if(curStam >= maxStam){
            this.curStam = maxStam;
        } else {
            this.curStam = curStam;
        }

        //this.curStam = curStam;
        DMZStatsCapabilities.syncStats(player);
    }

    public void removeCurStam(int curStam) {
        this.curStam -= curStam;

        if (this.curStam < 0) {
            this.curStam = 0;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addCurStam(int curStam) {
        var maxStam = 0;
        var maxVIDA = 0;

        maxVIDA = dmzdatos.calcularCON(races, constitution, 20, dmzClass);
        maxStam = dmzdatos.calcularSTM(races, maxVIDA);

        if(curStam >= maxStam){
            this.curStam = maxStam;
        } else {
            this.curStam += curStam;

            if(this.curStam > maxStam){
                this.curStam = maxStam;
            }
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public boolean isAcceptCharacter() {
        return AcceptCharacter;
    }

    public void setAcceptCharacter(boolean acceptCharacter) {
        AcceptCharacter = acceptCharacter;
        DMZStatsCapabilities.syncStats(player);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        DMZStatsCapabilities.syncStats(player);
    }

    public String getKiWeaponId() {
        return dmzKiWeapon;
    }

    public void setKiWeapon(String dmzKiWeapon) {
        this.dmzKiWeapon = dmzKiWeapon;
        DMZStatsCapabilities.syncStats(player);
    }
    public void setDmzClass(String dmzClass) {
        this.dmzClass = dmzClass;
        DMZStatsCapabilities.syncStats(player);
    }

    public void setDmzAlignment(int points) {
        if (points >= 100) {
            this.dmzAlignment = 100; // Máximo 100
        } else {
            this.dmzAlignment = points;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public void removeDmzAlignment(int puntos) {
        this.dmzAlignment -= puntos; // Resta 'puntos' a 'dmzAlignment'

        if (this.dmzAlignment < 0) {
            this.dmzAlignment = 0; // Limita el valor a un mínimo de 0
        }
        DMZStatsCapabilities.syncStats(player);
    }

    public void addDmzAlignment(int points) {
        dmzAlignment = Math.min(dmzAlignment + points, 100); // Esto limita el máximo a 100
        DMZStatsCapabilities.syncStats(player);
    }

    // Método para agregar una habilidad al mapa
    public void addSkill(String name, DMZSkill skill) {
        DMZSkills.put(name, skill);
        DMZStatsCapabilities.syncStats(player);
        DMZStatsCapabilities.syncSkills(player);
    }
    // Método para obtener una habilidad del mapa
    public DMZSkill getSkill(String name) {
        return DMZSkills.get(name);
    }
    public void setDMZSkills(Map<String, DMZSkill> DMZSkills) {
        this.DMZSkills = DMZSkills;
        DMZStatsCapabilities.syncStats(player);
        DMZStatsCapabilities.syncSkills(player);

    }
    // Método para verificar si una habilidad existe en el mapa
    public boolean hasSkill(String name) {
        return DMZSkills.containsKey(name);
    }
    public Map<String, DMZSkill> getDMZSkills() {
        return DMZSkills;

    }
    // Método para remover una habilidad del mapa
    public void removeSkill(String name) {
        DMZSkill skill = DMZSkills.get(name);

        if(skill != null){
            DMZSkills.remove(name);
        }

        DMZStatsCapabilities.syncStats(player);
        DMZStatsCapabilities.syncSkills(player);

    }

    public int getSkillLevel(String name) {
        DMZSkill skill = DMZSkills.get(name);
        return skill != null ? skill.getLevel() : -1;  // Devuelve -1 si no existe la habilidad
    }
    public boolean isActiveSkill(String name) {
        DMZSkill skill = DMZSkills.get(name);
        if (skill == null) {
            return false;
        }
        return skill.isActive();
    }

    public void setSkillActive(String name, boolean isActive){
        DMZSkill skill = DMZSkills.get(name);
        if(skill != null){
            skill.setActive(isActive);
        }

        DMZStatsCapabilities.syncStats(player);
        DMZStatsCapabilities.syncSkills(player);

    }
    public void setSkillLvl(String name, int cantidad){
        DMZSkill skill = DMZSkills.get(name);
        if(skill != null){
            skill.setLevel(cantidad);
        }

        DMZStatsCapabilities.syncStats(player);
        DMZStatsCapabilities.syncSkills(player);

    }
    // Métodos para gestionar los estados permanentes wa
    public void addDMZPermanentEffect(String permanentEffect, boolean isActive) {
        DMZPermanentEffects.put(permanentEffect, isActive);
        DMZStatsCapabilities.syncStats(player);

        DMZStatsCapabilities.syncPermanentEffects(player);

    }

    public Boolean getDMZPermaEffect(String permanentEffect) {
        return DMZPermanentEffects.getOrDefault(permanentEffect, false);
    }

    public Map<String, Boolean> getDMZPermanentEffects() {
        return DMZPermanentEffects;
    }

    public boolean hasDMZPermaEffect(String permanentEffect) {
        return DMZPermanentEffects.containsKey(permanentEffect);
    }

    public void setDMZPermanentEffect(String permanentEffect, boolean isActive) {
        if (DMZPermanentEffects.containsKey(permanentEffect)) {
            DMZPermanentEffects.put(permanentEffect, isActive);

            DMZStatsCapabilities.syncStats(player);
            DMZStatsCapabilities.syncPermanentEffects(player);

        }
    }

    public void removePermanentEffect(String permanentEffect) {
        if (DMZPermanentEffects.containsKey(permanentEffect)) {
            DMZPermanentEffects.remove(permanentEffect);

            DMZStatsCapabilities.syncStats(player);
            DMZStatsCapabilities.syncPermanentEffects(player);
        }

    }

    // Métodos para gestionar los estados temporales wa
    public Map<String, Integer> getDMZTemporalEffects() {
        return DMZTemporalEffects;
    }

    public void addDMZTemporalEffect(String temporalEffect, int seconds) {
        DMZTemporalEffects.put(temporalEffect, seconds);

        DMZStatsCapabilities.syncStats(player);
        DMZStatsCapabilities.syncTempEffects(player);


    }


    public Integer getDMZTemporalEffect(String temporaleffect) {
        return DMZTemporalEffects.getOrDefault(temporaleffect, 0);
    }

    public boolean hasDMZTemporalEffect(String temporaleffect) {
        return DMZTemporalEffects.containsKey(temporaleffect);
    }

    public void setDMZTemporalEffect(String permanentEffect, int seconds) {
        if (DMZTemporalEffects.containsKey(permanentEffect)) {
            DMZTemporalEffects.put(permanentEffect, seconds);

            DMZStatsCapabilities.syncStats(player);
            DMZStatsCapabilities.syncTempEffects(player);


        }
    }

    public void removeTemporalEffect(String temporalEffect) {
        if (DMZTemporalEffects.containsKey(temporalEffect)) {
            DMZTemporalEffects.remove(temporalEffect);

            DMZStatsCapabilities.syncStats(player);
            DMZStatsCapabilities.syncTempEffects(player);

        }
    }

    public CompoundTag saveNBTData() {

        CompoundTag nbt = new CompoundTag();

        nbt.putInt("race", races);

        nbt.putInt("hairID", hairID);
        nbt.putInt("bodyType", bodytype);
        nbt.putInt("eyesType", eyesType);

        nbt.putInt("strength", strength);
        nbt.putInt("defense", defense);
        nbt.putInt("constitution", constitution);
        nbt.putInt("kiPower", KiPower);
        nbt.putInt("energy", energy);

        nbt.putInt("currentEnergy", currentEnergy);
        nbt.putInt("currentStamina", curStam);
        nbt.putInt("dmzState", dmzState);
        nbt.putInt("dmzRelease", dmzRelease);

        nbt.putInt("bodyColor", bodyColor);
        nbt.putInt("bodyColor2", bodyColor2);
        nbt.putInt("bodyColor3", bodyColor3);
        nbt.putInt("hairColor", hairColor);
        nbt.putInt("eye1Color", eye1Color);
        nbt.putInt("eye2Color", eye2Color);
        nbt.putInt("auraColor", auraColor);
        nbt.putInt("dmzAlignment",dmzAlignment);

        nbt.putString("gender", gender);
        nbt.putString("dmzClass", dmzClass);
        nbt.putString("dmzskiweapon", dmzKiWeapon);

        nbt.putInt("zpoints", zpoints);
        nbt.putInt("dmzSenzuDaily", dmzSenzuDaily);
        nbt.putBoolean("acceptCharacter", AcceptCharacter);
        nbt.putBoolean("isAuraOn", isauraOn);
        nbt.putBoolean("isTurboOn", isTurbonOn);
        nbt.putBoolean("isDescendKey", isDescendkeyon);

        CompoundTag permanentEffectsTag = new CompoundTag();
        for (Map.Entry<String, Boolean> entry : DMZPermanentEffects.entrySet()) {
            permanentEffectsTag.putBoolean(entry.getKey(), entry.getValue());
        }
        nbt.put("DMZPermanentEffects", permanentEffectsTag);

        CompoundTag temporalEffectTag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : DMZTemporalEffects.entrySet()) {
            temporalEffectTag.putInt(entry.getKey(), entry.getValue());
        }
        nbt.put("DMZTemporalEffects", temporalEffectTag);

        // Crear un CompoundTag para guardar cada habilidad
        CompoundTag skillsTag = new CompoundTag();

        for (Map.Entry<String, DMZSkill> entry : DMZSkills.entrySet()) {
            String skillName = entry.getKey();
            DMZSkill skill = entry.getValue();

            // Crear un CompoundTag para la habilidad y guardarlo en el map de skills
            CompoundTag skillTag = new CompoundTag();

            // Aquí guardas los datos relevantes de la habilidad, como el nivel y la descripción
            skillTag.putString("name", skill.getName().getString());
            skillTag.putInt("level", skill.getLevel());
            skillTag.putString("description", skill.getDesc().getString());
            skillTag.putBoolean("active", skill.isActive());

            // Guarda la habilidad en el CompoundTag de skills
            skillsTag.put(skillName, skillTag);
        }

        nbt.put("DMZSkills", skillsTag);


        return nbt;
    }

    public void loadNBTData(CompoundTag nbt) {

        races = nbt.getInt("race");

        hairID = nbt.getInt("hairID");
        bodytype = nbt.getInt("bodyType");
        eyesType = nbt.getInt("eyesType");

        strength = nbt.getInt("strength");
        defense = nbt.getInt("defense");
        constitution = nbt.getInt("constitution");
        KiPower = nbt.getInt("kiPower");
        energy = nbt.getInt("energy");

        zpoints = nbt.getInt("zpoints");
        dmzSenzuDaily = nbt.getInt("dmzSenzuDaily");

        currentEnergy = nbt.getInt("currentEnergy");
        curStam = nbt.getInt("currentStamina");
        dmzState = nbt.getInt("dmzState");
        dmzRelease = nbt.getInt("dmzRelease");

        bodyColor = nbt.getInt("bodyColor");
        bodyColor2 = nbt.getInt("bodyColor2");
        bodyColor3 = nbt.getInt("bodyColor3");
        hairColor = nbt.getInt("hairColor");
        eye1Color = nbt.getInt("eye1Color");
        eye2Color = nbt.getInt("eye2Color");
        auraColor = nbt.getInt("auraColor");

        gender = nbt.getString("gender");
        dmzKiWeapon = nbt.getString("dmzskiweapon");
        dmzClass = nbt.getString("dmzClass");
        dmzAlignment = nbt.getInt("dmzAlignment");

        AcceptCharacter = nbt.getBoolean("acceptCharacter");
        isauraOn = nbt.getBoolean("isAuraOn");
        isTurbonOn = nbt.getBoolean("isTurboOn");
        isDescendkeyon = nbt.getBoolean("isDescendKey");

        CompoundTag permanentEffects = nbt.getCompound("DMZPermanentEffects");
        for (String effectName : permanentEffects.getAllKeys()) {
            boolean isActive = permanentEffects.getBoolean(effectName);
            DMZPermanentEffects.put(effectName, isActive);
        }

        CompoundTag temporalEffectsTag = nbt.getCompound("DMZTemporalEffects");
        for (String effectName : temporalEffectsTag.getAllKeys()) {
            int seconds = temporalEffectsTag.getInt(effectName);
            DMZTemporalEffects.put(effectName, seconds);
        }

        if (nbt.contains("DMZSkills", 10)) {  // Verifica si "DMZSkills" existe
            //El 10 hace referencia a TAG_COMPOUND

            CompoundTag skillsTag = nbt.getCompound("DMZSkills");

            for (String skillName : skillsTag.getAllKeys()) {
                CompoundTag skillTag = skillsTag.getCompound(skillName);

                // Cargar el nivel y la descripción de la habilidad
                String name = skillTag.getString("name");
                int level = skillTag.getInt("level");
                String description = skillTag.getString("description");
                boolean active = skillTag.getBoolean("active");

                // Crear el objeto DMZSkill y agregarlo al mapa
                DMZSkill skill = new DMZSkill(Component.literal(name), Component.literal(description), level, active);
                DMZSkills.put(skillName, skill);
            }
        }

    }

	public boolean isCompactMenu() {
		return compactMenu;
	}

	public void setCompactMenu(boolean compactMenu) {
        this.compactMenu = compactMenu;
    }
}
