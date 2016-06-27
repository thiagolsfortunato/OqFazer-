package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import br.com.fatec.oqfazer.api.dto.ParticipationDTO;

public class ContextParticipation implements Serializable{
	
	private static final long serialVersionUID = -4650843774060358681L;
	private ParticipationDTO participation;

	public ParticipationDTO getParticipation() {
		return participation;
	}

	public void setParticipation(ParticipationDTO participation) {
		this.participation = participation;
	}
	
}
