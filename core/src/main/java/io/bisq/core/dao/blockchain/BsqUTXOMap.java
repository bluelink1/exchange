/*
 * This file is part of Bitsquare.
 *
 * Bitsquare is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bitsquare is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bitsquare. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.core.dao.blockchain;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BsqUTXOMap {
    // We don't use a Lombok delegate here as we want control the access to our map
    private ObservableMap<TxIdIndexTuple, BsqUTXO> map = FXCollections.observableHashMap();
    private Set<String> txIdSet = new HashSet<>();

    public boolean containsTuple(String txId, int index) {
        return map.containsKey(new TxIdIndexTuple(txId, index));
    }

    public Object add(BsqUTXO bsqUTXO) {
        txIdSet.add(bsqUTXO.getTxId());
        return map.put(new TxIdIndexTuple(bsqUTXO.getTxId(), bsqUTXO.getIndex()), bsqUTXO);
    }

    public BsqUTXO getByTuple(String txId, int index) {
        return map.get(new TxIdIndexTuple(txId, index));
    }

    public BsqUTXO removeByTuple(String txId, int index) {
        txIdSet.remove(txId);
        return map.remove(new TxIdIndexTuple(txId, index));
    }

    public void addListener(MapChangeListener<TxIdIndexTuple, BsqUTXO> listener) {
        map.addListener(listener);
    }

    @Override
    public String toString() {
        return "BsqUTXOMap " + map.toString();
    }

    public Collection<BsqUTXO> values() {
        return map.values();
    }

    public Set<String> getTxIdSet() {
        return txIdSet;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

    public Set<Map.Entry<TxIdIndexTuple, BsqUTXO>> entrySet() {
        return map.entrySet();
    }
}
