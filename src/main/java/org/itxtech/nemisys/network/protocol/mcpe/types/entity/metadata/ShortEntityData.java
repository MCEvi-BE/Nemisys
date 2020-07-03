package org.itxtech.nemisys.network.protocol.mcpe.types.entity.metadata;


public class ShortEntityData extends EntityData<Integer> {
    public int data;

    public ShortEntityData(int id, int data) {
        super(id);
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        if (data == null) {
            this.data = 0;
        } else {
            this.data = data;
        }
    }

    @Override
    public int getType() {
        return EntityMetadata.DATA_TYPE_SHORT;
    }
}
