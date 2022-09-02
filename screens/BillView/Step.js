import { Button } from '@rneui/themed'
import React, { useContext, useEffect, useState } from 'react'

import { FlatList, View, Text, StyleSheet } from 'react-native'
import { t } from 'react-native-tailwindcss'

import { BillContext } from './BillContext'
import Item from '../../components/Item'

/**
 * @param {object} props
 * @param {import("xstate").State} props.machine
 */
export default function Step({ machine }) {
  const context = useContext(BillContext)

  const [billItems, setBillItems] = useState([])

  useEffect(() => {
    if (context.params?.newItem) {
      setBillItems([...billItems, context.params.newItem])
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [context.params?.newItem])

  const total = billItems.reduce((acc, item) => {
    return acc + Number(item.price) * Number(item.quantity)
  }, 0)

  return (
    <View style={{ flex: 1 }}>
      {machine.matches('idle') && (
        <View>
          <Text style={[t.textCenter, t.textXl, t.mT1]}>
            Add the Bill Items
          </Text>
          {billItems.length > 0 ? (
            <View>
              <FlatList
                style={styles.list}
                data={billItems}
                keyExtractor={(item) => item.id}
                renderItem={({ item }) => <Item item={item} />}
              />
              <Text style={[t.textCenter, t.textXl, t.mT1]}>
                Total: ${total.toFixed(2)}
              </Text>
            </View>
          ) : (
            <View style={[styles.noItemsText]}>
              <Text style={[t.textCenter]}>No items added yet</Text>
            </View>
          )}
        </View>
      )}
      <View style={[styles.addButton]}>
        <Button
          title="Add Item"
          onPress={() => context.navigation.navigate('AddNewItemModal')}
        />
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  addButton: {
    width: '85%',
    alignSelf: 'center',
    position: 'absolute',
    bottom: 80,
  },
  noItemsText: {
    alignSelf: 'center',
    marginTop: '50%',
  },
  list: {
    marginTop: 20,
  },
})
