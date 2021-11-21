package fr.simple.teams.assaut;

import java.util.HashMap;
import java.util.Map;

public class AssautData {

	private String attaquants = null;
	private String défenceurs = null;
	private String accès = "open";
	private String phase = "préparation";
	private int time = 0;
	private int tpsPréparation = 50;
	private int tpsAttaque = 120;
	private int blocks = 0;
	//private int mise = 1000;
	
	private String winner = null;
	private String winnerTeam = null;
	private float ptA = 0;
	private float ptD = 0;
	
	private int killsAttaquants = 0;
	private int killsDéfenceurs = 0;
	
	private boolean bankChestIsBreaked = false;
	
	public static Map<String, AssautData> AssautData = new HashMap<>();
	
	public AssautData(String attaquants) {
		this.setAttaquants(attaquants);
		AssautData.put(attaquants, this);
	}

	public String getAttaquants() {
		return attaquants;
	}

	public void setAttaquants(String attaquants) {
		this.attaquants = attaquants;
	}

	public String getDéfenceurs() {
		return défenceurs;
	}

	public void setDéfenceurs(String défenceurs) {
		this.défenceurs = défenceurs;
	}

	public String getAccès() {
		return accès;
	}

	public void setAccès(String accès) {
		this.accès = accès;
	}

	public int getTpsPréparation() {
		return tpsPréparation;
	}

	public void setTpsPréparation(int tpsPréparation) {
		this.tpsPréparation = tpsPréparation;
	}

	public int getTpsAttaque() {
		return tpsAttaque;
	}

	public void setTpsAttaque(int tpsAttaque) {
		this.tpsAttaque = tpsAttaque;
	}

	public int getKillsAttaquants() {
		return killsAttaquants;
	}

	public void setKillsAttaquants(int killsAttaquants) {
		this.killsAttaquants = killsAttaquants;
	}

	public int getKillsDéfenceurs() {
		return killsDéfenceurs;
	}

	public void setKillsDéfenceurs(int killsDéfenceurs) {
		this.killsDéfenceurs = killsDéfenceurs;
	}

	public boolean isBankChestIsBreaked() {
		return bankChestIsBreaked;
	}

	public void setBankChestIsBreaked(boolean bankChestIsBreaked) {
		this.bankChestIsBreaked = bankChestIsBreaked;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public float getPtA() {
		return ptA;
	}

	public void setPtA(float ptsAtt) {
		this.ptA = ptsAtt;
	}

	public float getPtD() {
		return ptD;
	}

	public void setPtD(float ptsDef) {
		this.ptD = ptsDef;
	}

	public String getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}
	
}
