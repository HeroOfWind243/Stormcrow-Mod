package stormcrowmod.cardmods;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static stormcrowmod.StormcrowMod.makeID;

public class OnDamageGainBlock extends AbstractDamageModifier {
    public static final String ID = makeID("BlockOnHit");
    private final int block;

    public OnDamageGainBlock(int amount) {
        this.block = amount;
    }

    //This hook grabs the lastDamageTaken once it is updated upon attacking the monster.
    //This lets us heal the attacker equal to the damage that was actually dealt to the target
    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature targetHit) {
        if (lastDamageTaken > 0) {
            this.addToBot(new GainBlockAction(info.owner, info.owner, this.block));
        }
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return null;
    }
}
