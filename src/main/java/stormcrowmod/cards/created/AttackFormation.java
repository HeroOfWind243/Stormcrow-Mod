package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class AttackFormation extends BaseCard {
    public static final String ID = makeID(AttackFormation.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 4;

    public AttackFormation() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer pl = AbstractDungeon.player;

        addToBot(new ImpulseAction(pl, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AttackFormation();
    }
}
