import React from 'react'

import { ListItem } from '@rneui/base'

export default function ItemBill({ bill, onPress }) {
  return (
    <ListItem bottomDivider onPress={() => (onPress ? onPress(bill) : null)}>
      <ListItem.Content>
        <ListItem.Title>{bill.name}</ListItem.Title>
      </ListItem.Content>
      <ListItem.Chevron name="chevron-right" type="entypo" />
    </ListItem>
  )
}
