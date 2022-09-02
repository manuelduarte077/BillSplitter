import React, { useEffect, useState } from 'react'
import { t } from 'react-native-tailwindcss'

import { FlatList, View, Text, StyleSheet } from 'react-native'
import { Icon } from '@rneui/base'
import ItemBill from '../components/ItemBill'

export function Home({ navigation, route }) {
  const [bills, setBills] = useState([{ id: 1, name: 'test' }])
  const newBill = route.params?.newBill

  useEffect(() => {
    if (newBill) {
      setBills([...bills, newBill])
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [newBill])

  return (
    <View style={{ height: '100%' }}>
      {bills.length === 0 ? (
        <Text style={[style.noBills, t.textGray500]}>
          You don't have bills yet
        </Text>
      ) : (
        <FlatList
          style={style.list}
          data={bills}
          keyExtractor={(item) => item.id}
          renderItem={({ item }) => (
            <ItemBill
              bill={item}
              onPress={(bill) => navigation.navigate('BillView', { bill })}
            />
          )}
        />
      )}
      <View style={style.newBillButton}>
        <Icon
          reverse
          name="plus"
          type="antdesign"
          color="#517fa4"
          onPress={() => navigation.navigate('NewBillModal')}
        />
      </View>
    </View>
  )
}

const style = StyleSheet.create({
  newBillButton: {
    position: 'absolute',
    bottom: 10,
    right: 10,
  },

  noBills: {
    textAlign: 'center',
    fontSize: 18,
    marginTop: 40,
  },

  list: {
    marginTop: 20,
  },
})
