package com.example.phoneforfilm.presentation.viewmodel

<layout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto" />

<data>
<variable
name="viewModel"
type="com.example.phoneforfilm.viewmodel.ChatViewModel" />
</data>

<androidx.constraintlayout.widget.ConstraintLayout
android:layout_width="match_parent"
android:layout_height="match_parent">

<androidx.recyclerview.widget.RecyclerView
android:id="@+id/recyclerViewMessages"
android:layout_width="0dp"
android:layout_height="0dp"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toTopOf="@+id/chatInputLayout"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
tools:listitem="@layout/item_message_sent" />

<LinearLayout
android:id="@+id/chatInputLayout"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:padding="8dp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent">

<EditText
$1android:layout_height="wrap_content"
android:minHeight="48dp"
android:layout_weight="1"
android:autofillHints="text"
android:hint="@string/type_message"
android:inputType="textMultiLine|textCapSentences"
android:maxLines="5"
android:textColor="@android:color/black"
android:textColorHint="@color/gray_hint_color" />

<ImageButton
android:id="@+id/buttonSend"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:src="@drawable/ic_send"
android:contentDescription="@string/send"
android:background="?attr/selectableItemBackgroundBorderless" />
</LinearLayout>
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
