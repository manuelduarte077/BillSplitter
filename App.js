import React from 'react'

import 'react-native-get-random-values'

import { NavigationContainer } from '@react-navigation/native'
import { ThemeProvider } from '@rneui/themed'
import { createStackNavigator } from '@react-navigation/stack'

import { Home } from './screens/Home'
import BillView from './screens/BillView/index'
import AppTheme from './theme'
import NewBillModal from './screens/modals/NewBillModal'
import AddNewItemModal from './screens/modals/AddNewItemModal'

const RootStack = createStackNavigator()

export default function App() {
  return (
    <ThemeProvider theme={AppTheme}>
      <NavigationContainer>
        <RootStack.Navigator>
          <RootStack.Group>
            <RootStack.Screen
              name="Home"
              component={Home}
              options={{ title: 'My Saved Bills' }}
            />
            <RootStack.Screen
              name="BillView"
              component={BillView}
              options={({ route }) => ({
                title: route.params.bill?.name ?? 'Bill',
              })}
            />
          </RootStack.Group>
          <RootStack.Group screenOptions={{ presentation: 'modal' }}>
            <RootStack.Screen
              name="NewBillModal"
              options={{ title: 'Create a new Bill' }}
              component={NewBillModal}
            />
            <RootStack.Screen
              name="AddNewItemModal"
              options={{ title: 'Add a new Item' }}
              component={AddNewItemModal}
            />
          </RootStack.Group>
        </RootStack.Navigator>
      </NavigationContainer>
    </ThemeProvider>
  )
}
