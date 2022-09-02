import React from 'react'

import { useMachine } from '@xstate/react'
import { View } from 'react-native'

import BillMachine from './machine'
import StepsFooter from './StepsFooter'
import Step from './Step'
import { BillContext } from './BillContext'

export default function BillView({ navigation, route }) {
  const [machine] = useMachine(BillMachine)

  const { bill = {} } = route.params

  return (
    <View style={{ flex: 1 }}>
      <BillContext.Provider
        value={{ machine, bill, navigation, params: route.params }}
      >
        <Step machine={machine} />
        <StepsFooter />
      </BillContext.Provider>
    </View>
  )
}
