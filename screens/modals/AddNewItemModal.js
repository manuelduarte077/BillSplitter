import React, { useCallback, useState } from 'react'

import { View, Alert, StyleSheet, Text } from 'react-native'
import { Button, Input } from '@rneui/themed'
import { v4 as uuidv4 } from 'uuid'

export default function AddNewItemModal({ navigation }) {
  const [description, setDescription] = useState('')
  const [quantity, setQuantity] = useState(1)
  const [price, setPrice] = useState('')

  const handleSubmit = useCallback(() => {
    if (!description) {
      Alert.alert('Description is required')
      return
    }

    const newItem = {
      id: uuidv4(),
      description,
      quantity,
      price,
    }

    navigation.navigate({
      name: 'BillView',
      params: { newItem },
      merge: true,
    })
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [description, quantity, price])

  return (
    <View style={[styles.container]}>
      <View>
        <Text>description: {description}</Text>
        <Text>quantity: {quantity}</Text>
        <Text>price: {price}</Text>
      </View>
      <Input
        placeholder="Description"
        value={description}
        onChangeText={(val) => setDescription(val)}
      />
      <Input
        placeholder="Quantity"
        keyboardType="numeric"
        value={quantity}
        onChangeText={(val) => setQuantity(val)}
      />
      <Input
        placeholder="Price"
        keyboardType="numeric"
        value={price}
        onChangeText={(val) => setPrice(val)}
      />
      <Button title="Save" onPress={handleSubmit} />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    marginHorizontal: 20,
    marginTop: 20,
  },
})
