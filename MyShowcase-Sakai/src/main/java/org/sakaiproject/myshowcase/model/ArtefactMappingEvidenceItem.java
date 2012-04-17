//***************************************************************************
//*MyShowcase                                                               * 
//*Copyright (C) 2010 MyKnowledgeMap Ltd.                                   *
//*                                                                         *
//*This program is free software: you can redistribute it and/or modify     *
//*it under the terms of the GNU Affero General Public License as           *
//*published by the Free Software Foundation, either version 3 of the       *
//*License, or (at your option) any later version.                          *
//*                                                                         *
//*This program is distributed in the hope that it will be useful,          *
//*but WITHOUT ANY WARRANTY; without even the implied warranty of           *
//*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            * 
//*GNU Affero General Public License for more details.                      *
//*                                                                         *
//*You should have received a copy of the GNU Affero General Public License *
//*along with this program.  If not, see <http://www.gnu.org/licenses/>.    *
//*                                                                         *
//*Web: <http://www.my-showcase.org/>.                                      *
//*Email: <myshowcase@myknowledgemap.com>                                   *
//*                                                                         *
//***************************************************************************

package org.sakaiproject.myshowcase.model;

import java.io.Serializable;


	public class ArtefactMappingEvidenceItem implements Serializable  {
		
		private static final long serialVersionUID = 1L;
		private String competency;
		private String mapping;
		private long artefactMappingId;
		private long competencyId;
		private long mappingId;
		
		/**
		 * Default constructor
		 */
		public ArtefactMappingEvidenceItem() {
		}
		
		public String getMapping() {
			return mapping;
		}

		
		public void setMapping(String mapping) {
			this.mapping = mapping;
		}
		
		public String getCompetency() {
			return competency;
		}

		
		public void setCompetency(String competency) {
			this.competency = competency;
		}
		
		public long getArtefactMappingId() {
			return artefactMappingId;
		}

		
		public void setArtefactMappingId(long artefactMappingId) {
			this.artefactMappingId = artefactMappingId;
		}
		
		public long getCompetencyId() {
			return competencyId;
		}

		
		public void setCompetencyId(long competencyId) {
			this.competencyId = competencyId;
		}
		
		public long getMappingId() {
			return mappingId;
		}

		
		public void setMappingId(long mappingId) {
			this.mappingId = mappingId;
		}
}
