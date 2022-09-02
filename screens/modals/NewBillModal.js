import React, { useCallback, useState } from 'react'

import { View, Alert } from 'react-native'
import { Button, Input } from '@rneui/themed'
import { v4 as uuidv4 } from 'uuid'

export default function NewBillModal({ navigation }) {
  const [name, setName] = useState('')

  const handleSubmit = useCallback(() => {
    if (!name) {
      Alert.alert('Name is required')
      return
    }

    const newBill = {
      id: uuidv4(),
      name,
    }

    navigation.navigate('Home', { newBill })
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [name])

  return (
    <View>
      <Input
        placeholder="Name"
        value={name}
        onChangeText={(val) => setName(val)}
      />
      <Button title="Save" onPress={handleSubmit} />
    </View>
  )
}
