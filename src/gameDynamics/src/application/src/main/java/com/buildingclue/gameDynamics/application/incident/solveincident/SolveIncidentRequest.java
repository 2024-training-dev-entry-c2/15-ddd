package com.buildingclue.gameDynamics.application.incident.solveincident;

import com.buildingclue.shared.application.Request;

public class SolveIncidentRequest extends Request {

  public SolveIncidentRequest(){
    super(null);
  }

  public SolveIncidentRequest(String aggregateId) {
    super(aggregateId);
  }
}
