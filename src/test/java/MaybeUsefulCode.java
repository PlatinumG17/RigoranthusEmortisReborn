

/*
    static OnHitEffect armorBypassingDamageMod(float additionalDamage, EnumAspect aspect) {
        return (stack, target, attacker) -> {
            DamageSource source;
            float damage = additionalDamage * 3.3F;

            if(attacker instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) attacker;
                source = DamageSource.playerAttack(serverPlayer);
                Title title = PlayerSavedData.getData(serverPlayer).getTitle();

                if(target instanceof LanguidDwellerEntity) {
                    float modifier = (float) (PlayerSavedData.getData(serverPlayer).getAspectProgress().getAspectDamageModifier());

                    if(title == null || title.getHeroAspect() != aspect)
                        modifier = modifier / 1.2F;

                    damage = damage * modifier;
                } else {
                    if(title == null || title.getHeroAspect() != aspect)
                        damage = damage / 1.2F;
                }
            } else {source = DamageSource.mobAttack(attacker);}
            target.hurt(source.bypassArmor(), damage);
        };
    }
 */