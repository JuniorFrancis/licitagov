package com.exame.licitagov.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(value = { "_links" })
public class Bidding {

    public Bidding(
            Integer uasg,
            Integer modality,
            Integer warningNumber,
            String identifier,
            Integer solicitationItemNumber,
            String pregao,
            String warningSituation,
            String object,
            Integer catalogId,
            String processNumber,
            String generalInformations,
            String resourceType,
            Integer numberItems,
            String responsible,
            String responsibleFunction,
            String dateDeliveryEdital,
            String addressDeliveryEdital,
            String dateOpeningProposal,
            String proposalDeliveryDate,
            String publicationDate,
            boolean isVisualized
    ) {
        this.uasg = uasg;
        this.modality = modality;
        this.warningNumber = warningNumber;
        this.identifier = identifier;
        this.solicitationItemNumber = solicitationItemNumber;
        this.pregao = pregao;
        this.warningSituation = warningSituation;
        this.object = object;
        this.catalogId = catalogId;
        this.processNumber = processNumber;
        this.generalInformations = generalInformations;
        this.resourceType = resourceType;
        this.numberItems = numberItems;
        this.responsible = responsible;
        this.responsibleFunction = responsibleFunction;
        this.dateDeliveryEdital = dateDeliveryEdital;
        this.addressDeliveryEdital = addressDeliveryEdital;
        this.dateOpeningProposal = dateOpeningProposal;
        this.proposalDeliveryDate = proposalDeliveryDate;
        this.publicationDate = publicationDate;
        this.isVisualized = isVisualized;
    }

    public Bidding() {
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private Integer uasg;

    @JsonProperty("modalidade")
    private Integer modality;

    @JsonProperty("numero_aviso")
    private Integer warningNumber;

    @JsonProperty("identificador")
    private String identifier;

    @JsonProperty("numero_item_licitacao")
    private Integer solicitationItemNumber;

    @JsonProperty("tipo_pregao")
    private String pregao;

    @JsonProperty("situacao_aviso")
    private String warningSituation;

    @JsonProperty("objeto")
    private String object;

    @JsonProperty("codigo_do_item_no_catalogo")
    private Integer catalogId;

    @JsonProperty("numero_processo")
    private String processNumber;

    @JsonProperty("informacoes_gerais")
    private String generalInformations;

    @JsonProperty("tipo_recurso")
    private String resourceType;

    @JsonProperty("numero_itens")
    private Integer numberItems;

    @JsonProperty("nome_responsavel")
    private String responsible;

    @JsonProperty("funcao_responsavel")
    private String responsibleFunction;

    @JsonProperty("data_entrega_edital")
    private String dateDeliveryEdital;

    @JsonProperty("endereco_entrega_edital")
    private String addressDeliveryEdital;

    @JsonProperty("data_abertura_proposta")
    private String dateOpeningProposal;

    @JsonProperty("data_entrega_proposta")
    private String proposalDeliveryDate;

    @JsonProperty("data_publicacao")
    private String publicationDate;

    private boolean isVisualized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUasg() {
        return uasg;
    }

    public void setUasg(Integer uasg) {
        this.uasg = uasg;
    }

    public Integer getModality() {
        return modality;
    }

    public void setModality(Integer modality) {
        this.modality = modality;
    }

    public Integer getWarningNumber() {
        return warningNumber;
    }

    public void setWarningNumber(Integer warningNumber) {
        this.warningNumber = warningNumber;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getSolicitationItemNumber() {
        return solicitationItemNumber;
    }

    public void setSolicitationItemNumber(Integer solicitationItemNumber) {
        this.solicitationItemNumber = solicitationItemNumber;
    }

    public String getPregao() {
        return pregao;
    }

    public void setPregao(String pregao) {
        this.pregao = pregao;
    }

    public String getWarningSituation() {
        return warningSituation;
    }

    public void setWarningSituation(String warningSituation) {
        this.warningSituation = warningSituation;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        if(object != null && object.length() >= 255){
            this.object = object.substring(0, 254);
            return;
        }

        this.object = object;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getGeneralInformations() {
        return generalInformations;
    }

    public void setGeneralInformations(String generalInformations) {
        if(generalInformations != null && generalInformations.length() >= 255){
            this.generalInformations = generalInformations.substring(0, 254);;
            return;
        }

        this.generalInformations = generalInformations;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(Integer numberItems) {
        this.numberItems = numberItems;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getResponsibleFunction() {
        return responsibleFunction;
    }

    public void setResponsibleFunction(String responsibleFunction) {
        this.responsibleFunction = responsibleFunction;
    }

    public String getDateDeliveryEdital() {
        return dateDeliveryEdital;
    }

    public void setDateDeliveryEdital(String dateDeliveryEdital) {
        this.dateDeliveryEdital = dateDeliveryEdital;
    }

    public String getAddressDeliveryEdital() {
        return addressDeliveryEdital;
    }

    public void setAddressDeliveryEdital(String addressDeliveryEdital) {
        this.addressDeliveryEdital = addressDeliveryEdital;
    }

    public String getDateOpeningProposal() {
        return dateOpeningProposal;
    }

    public void setDateOpeningProposal(String dateOpeningProposal) {
        this.dateOpeningProposal = dateOpeningProposal;
    }

    public String getProposalDeliveryDate() {
        return proposalDeliveryDate;
    }

    public void setProposalDeliveryDate(String proposalDeliveryDate) {
        this.proposalDeliveryDate = proposalDeliveryDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate.replace("-", "");
    }

    public boolean isVisualized() {
        return isVisualized;
    }

    public void setVisualisation(boolean visualized) {
        isVisualized = visualized;
    }

    public static class Builder {

        public Integer uasg;

        public Integer modality;

        public Integer warningNumber;

        public String identifier;

        public Integer solicitationItemNumber;

        public String pregao;

        public String warningSituation;

        public String object;

        public Integer catalogId;

        public String processNumber;

        public String generalInformations;

        public String resourceType;

        public Integer numberItems;

        public String responsible;

        public String responsibleFunction;

        public String dateDeliveryEdital;

        public String addressDeliveryEdital;

        public String dateOpeningProposal;

        public String proposalDeliveryDate;

        public String publicationDate;

        public boolean isVisualized;

        public Builder withUasg(Integer uasg){
            this.uasg = uasg;
            return this;
        }

        public Builder withModality(Integer modality){
            this.modality = modality;
            return this;
        }

        public Builder withWarningNumber(Integer warningNumber){
            this.warningNumber = warningNumber;
            return this;
        }

        public Builder withIdentifier(String identifier){
            this.identifier = identifier;
            return this;
        }

        public Builder withSolicitationItemNumber(Integer solicitationItemNumber){
            this.solicitationItemNumber = solicitationItemNumber;
            return this;
        }

        public Builder withPregao(String pregao){
            this.pregao = pregao;
            return this;
        }

        public Builder withWarningSituation(String warningSituation){
            this.warningSituation = warningSituation;
            return this;
        }

        public Builder withObject(String object){
            this.object = object;
            return this;
        }

        public Builder withCatalogId(Integer catalogId){
            this.catalogId = catalogId;
            return this;
        }

        public Builder withProcessNumber(String processNumber){
            this.processNumber = processNumber;
            return this;
        }

        public Builder withGeneralInformations(String generalInformations){
            this.generalInformations = generalInformations;
            return this;
        }

        public Builder withResourceType(String reesourceType){
            this.resourceType = resourceType;
            return this;
        }

        public Builder withNumberItems(Integer numberItems){
            this.numberItems = numberItems;
            return this;
        }

        public Builder withResponsible(String responsible){
            this.responsible = responsible;
            return this;
        }

        public Builder withResponsibleFunction(String responsibleFunction){
            this.responsibleFunction = responsibleFunction;
            return this;
        }

        public Builder withDateDeliveryEdital(String dateDeliveryEdital){
            this.dateDeliveryEdital = dateDeliveryEdital;
            return this;
        }

        public Builder withAddressDeliveryEdital(String addressDeliveryEdital){
            this.addressDeliveryEdital = addressDeliveryEdital;
            return this;
        }

        public Builder withDateOpeningProposal(String dateOpeningProposal){
            this.dateOpeningProposal = dateOpeningProposal;
            return this;
        }

        public Builder withProposalDeliveryDate(String proposalDeliveryDate){
            this.proposalDeliveryDate = proposalDeliveryDate;
            return this;
        }

        public Builder withPublicationDate(String publicationDate){
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withVisualization(boolean isVisualized){
            this.isVisualized = isVisualized;
            return this;
        }

        public Bidding build() {
            return new Bidding(
                    uasg,
                    modality,
                    warningNumber,
                    identifier,
                    solicitationItemNumber,
                    pregao,
                    warningSituation,
                    object,
                    catalogId,
                    processNumber,
                    generalInformations,
                    resourceType,
                    numberItems,
                    responsible,
                    responsibleFunction,
                    dateDeliveryEdital,
                    addressDeliveryEdital,
                    dateOpeningProposal,
                    proposalDeliveryDate,
                    publicationDate,
                    isVisualized
            );
        }
    }
}
