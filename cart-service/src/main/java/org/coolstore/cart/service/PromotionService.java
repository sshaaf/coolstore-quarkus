package org.coolstore.cart.service;

import org.coolstore.cart.model.Cart;
import org.coolstore.cart.model.CartItem;
import org.coolstore.cart.model.Promotion;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class PromotionService {


    private String name = null;

    private Set<Promotion> promotionSet = null;

    public PromotionService() {
        promotionSet = new HashSet<Promotion>();
        promotionSet.add(new Promotion("329299", .25));
    }

    public void applyCartItemPromotions(Cart cart) {
        if (cart != null && cart.getCartItemList().size() > 0) {
            Map<String, Promotion> promoMap = new HashMap<String, Promotion>();
            for (Promotion promo : getPromotions()) {
                promoMap.put(promo.getItemId(), promo);
            }

            for (CartItem sci : cart.getCartItemList()) {
                String productId = sci.getProduct().getItemId();
                Promotion promo = promoMap.get(productId);
                if (promo != null) {
                    //sci.setPromoSavings(sci.getProduct().getPrice() * promo.getPercentOff() * -1);
                    sci.setPrice(sci.getProduct().getPrice() * (1 - promo.getPercentOff()));
                }
            }
        }

    }

    public void applyShippingPromotions(Cart cart) {
        if (cart != null) {
            //PROMO: if cart total is greater than 75, free shipping
            if (cart.getCartItemTotal() >= 75) {
                cart.setShippingPromoSavings(cart.getShippingTotal() * -1);
                cart.setShippingTotal(0);

            }

        }

    }

    public Set<Promotion> getPromotions() {
        if (promotionSet == null) {
            promotionSet = new HashSet<Promotion>();
        }

        return new HashSet<Promotion>(promotionSet);
    }

    public void setPromotions(Set<Promotion> promotionSet) {
        if (promotionSet != null) {
            this.promotionSet = new HashSet<Promotion>(promotionSet);

        } else {
            this.promotionSet = new HashSet<Promotion>();
        }
    }

    @Override
    public String toString() {
        return "PromoService [name=" + name + ", promotionSet=" + promotionSet
                + "]";
    }
}