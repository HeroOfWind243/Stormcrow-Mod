package stormcrowmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

import java.util.List;
import java.util.function.Consumer;

public class Reload extends BaseCard {
    public static final String ID = makeID(Reload.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Reload() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Consumer<List<AbstractCard>> cards = c -> {
          for (AbstractCard card : c) {
              if (card.type == CardType.ATTACK || card.type == CardType.POWER) {
                  addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1, true));
              }

              if (card.type == CardType.SKILL || card.type == CardType.POWER) {
                  addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1, true));
              }
          }
        };

        addToBot(new MoveCardsAction(p.hand, p.discardPile, c -> true, this.magicNumber, cards));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Reload();
    }
}
