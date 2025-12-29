package com.ykaro.attackontitan;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ODMGearModel<T extends LivingEntity> extends HumanoidModel<T> {
    public ODMGearModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Aqui é onde "desenhamos" as turbinas nas pernas
        PartDefinition rightLeg = partdefinition.getChild("right_leg");
        rightLeg.addOrReplaceChild("turbine_right", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.offset(-1.1F, 0.0F, 0.0F));

        PartDefinition leftLeg = partdefinition.getChild("left_leg");
        leftLeg.addOrReplaceChild("turbine_left", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.offset(1.1F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}