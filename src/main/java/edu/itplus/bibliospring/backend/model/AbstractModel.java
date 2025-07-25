package edu.itplus.bibliospring.backend.model;

import java.util.Objects;
import java.util.UUID;

public class AbstractModel {

    private String uuid;

    public String getUUID() {
        if(uuid==null){
            uuid= UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void setUUID(String UUID) {

        this.uuid= UUID;
    }

    @Override
    public String toString() {
        return "AbstractModel{" + "uuid=" + uuid + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractModel that = (AbstractModel) o;
        return Objects.equals(getUUID(), that.getUUID());
    }
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
