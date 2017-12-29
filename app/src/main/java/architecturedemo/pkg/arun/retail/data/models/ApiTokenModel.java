package architecturedemo.pkg.arun.retail.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by kamal on 30/10/17.
 */

public class ApiTokenModel implements Serializable {

    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("token")
    private String token;
    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("conversationId")
    public String getConversationId() {
        return conversationId;
    }

    @JsonProperty("conversationId")
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

}
