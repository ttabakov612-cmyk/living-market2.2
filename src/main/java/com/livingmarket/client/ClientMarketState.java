package com.livingmarket.client;

import java.util.Collections;
import java.util.List;

public final class ClientMarketState {
    public static final byte KIND_NORMAL = 0;
    public static final byte KIND_PINNED = 1;
    public static final byte KIND_DAILY_DEAL = 2;
    public static final byte KIND_RARE_BARTER = 3;

    private static int containerId = -1;
    private static boolean enabled = false;
    private static List<Byte> offerKinds = Collections.emptyList();
    private static int rerollCost = 1;
    private static int maxPins = 2;
    private static int uniqueProfessions = 1;
    private static int barterChancePercent = 4;
    private static String biomeRegionId = "plains_forest";

    private ClientMarketState() {}

    public static void update(
            int newContainerId,
            boolean isEnabled,
            List<Byte> newOfferKinds,
            int newRerollCost,
            int newMaxPins,
            int newUniqueProfessions,
            int newBarterChancePercent,
            String newBiomeRegionId
    ) {
        containerId = newContainerId;
        enabled = isEnabled;
        offerKinds = List.copyOf(newOfferKinds);
        rerollCost = newRerollCost;
        maxPins = newMaxPins;
        uniqueProfessions = newUniqueProfessions;
        barterChancePercent = newBarterChancePercent;
        biomeRegionId = newBiomeRegionId != null ? newBiomeRegionId : "plains_forest";
    }

    public static boolean isEnabledFor(int currentContainerId) {
        return enabled && containerId == currentContainerId;
    }

    public static byte getOfferKind(int index) {
        if (index < 0 || index >= offerKinds.size()) {
            return KIND_NORMAL;
        }
        return offerKinds.get(index);
    }

    public static boolean isPinned(int index) {
        return getOfferKind(index) == KIND_PINNED;
    }

    public static boolean isDailyDeal(int index) {
        return getOfferKind(index) == KIND_DAILY_DEAL;
    }

    public static boolean isRareBarter(int index) {
        return getOfferKind(index) == KIND_RARE_BARTER;
    }

    public static boolean isSpecial(int index) {
        byte k = getOfferKind(index);
        return k == KIND_DAILY_DEAL || k == KIND_RARE_BARTER;
    }

    public static int getPinnedCount() {
        int count = 0;
        for (Byte k : offerKinds) {
            if (k != null && k == KIND_PINNED) {
                count++;
            }
        }
        return count;
    }

    public static int getNormalUnpinnedCount() {
        int count = 0;
        for (Byte k : offerKinds) {
            if (k != null && k == KIND_NORMAL) {
                count++;
            }
        }
        return count;
    }

    public static int getRerollCost() {
        return rerollCost;
    }

    public static int getMaxPins() {
        return maxPins;
    }

    public static int getUniqueProfessions() {
        return uniqueProfessions;
    }

    public static int getBarterChancePercent() {
        return barterChancePercent;
    }

    public static String getBiomeRegionId() {
        return biomeRegionId;
    }
}
