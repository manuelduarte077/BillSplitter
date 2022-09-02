import React from 'react'

import { ListItem } from '@rneui/base'
import { View, Text, StyleSheet } from 'react-native'
import { t } from 'react-native-tailwindcss'

export default function Item({ item, onPress }) {
  console.log({ item })
  return (
    <ListItem bottomDivider onPress={() => (onPress ? onPress(item) : null)}>
      <ListItem.Content>
        <View style={styles.container}>
          <ListItem.Title>{item.description}</ListItem.Title>
          <Text style={styles.price}>C${Number(item.price).toFixed(2)}</Text>
        </View>
        <ListItem.Subtitle style={t.textGray500}>
          x{item.quantity}
        </ListItem.Subtitle>
      </ListItem.Content>
    </ListItem>
  )
}

const styles = StyleSheet.create({
  container: {
    justifyContent: 'space-between',
    flexDirection: 'row',
    width: '100%',
  },
  description: {
    flexGrow: 2,
    marginRight: 10,
  },
  quantity: {
    flexGrow: 1,
    marginRight: 10,
  },
  price: {
    fontWeight: 'bold',
  },
})
