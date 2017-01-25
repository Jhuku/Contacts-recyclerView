package com.filetransfer.shuvam.contactsrecview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Group> groups;
    private  RecyclerView mainRv;
    private RecyclerViewAdapter adapter;
    private ArrayList<Group> peopleGroup;
    private ArrayList<Person> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);



        groups = new ArrayList<Group>();
        peopleGroup = new ArrayList<Group>();
        people = new ArrayList<Person>();
        mainRv = (RecyclerView)findViewById(R.id.mainRecView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mainRv.setLayoutManager(lm);

        for(int i=0;i<5;i++)
        {
            people.clear();
            Group g = new Group();
            for(int j=0;j<4;j++)
            {
                Person p = new Person();
                if(j==0)
                    p.setName("Shuvam");
                else if(j==1)
                    p.setName("ScarJo");
                else
                    p.setName("Jason");
                if(j==1)
                    p.setImgRes(R.drawable.scarjo);
                else if(j==0)
                    p.setImgRes(R.drawable.shuvam);
                else
                    p.setImgRes(R.drawable.propic);
                people.add(p);
            }
            g.setGroup(people);
            peopleGroup.add(g);
        }

        adapter = new RecyclerViewAdapter(peopleGroup);
        mainRv.setAdapter(adapter);

    }
    private class RecyclerViewAdapter extends RecyclerView.Adapter<MyView> {


        RecyclerViewAdapter(ArrayList<Group> group){
            groups= group;
        }
        @Override
        public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rec_view_item,parent,false);
            return new MyView(view);
        }

        @Override
        public void onBindViewHolder(MyView holder, int position) {

        }

        @Override
        public int getItemCount() {
            return groups.size();
        }



    }
    private class MyView extends RecyclerView.ViewHolder{
        private RecyclerView childRecView;
        private MyView.ChildRecyclerViewAdapter adapterChild;
        private ArrayList<Person> children;
        public MyView(View itemView) {
            super(itemView);

            childRecView = (RecyclerView)itemView.findViewById(R.id.childRecView);
            GridLayoutManager layoutManager = new GridLayoutManager(itemView.getContext(),3);
            childRecView.setLayoutManager(layoutManager);
            children = new ArrayList<Person>();
            for (Group ppl : groups)
            {
                
                adapterChild = new MyView.ChildRecyclerViewAdapter(ppl.getGroup());
                childRecView.setAdapter(adapterChild);
            }


        }




        private class ChildRecyclerViewAdapter extends RecyclerView.Adapter<MyView.MyChildViews> {

            ArrayList<Person> people;
            ChildRecyclerViewAdapter(ArrayList<Person> people){
                this.people=people;
            }
            @Override
            public MyView.MyChildViews onCreateViewHolder(ViewGroup parent, int viewType) {
                View viewChild = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rec_view_item,parent,false);
                return new MyView.MyChildViews(viewChild);
            }

            @Override
            public void onBindViewHolder(MyView.MyChildViews holder, int position) {

                holder.contactName.setText(""+people.get(position).getName());
                holder.contactDp.setImageResource(people.get(position).getImgRes());

                if(position==2)
                {
                    holder.contactDp.setBorderWidth(6);
                    holder.contactDp.setBorderColor(getResources().getColor(R.color.checked));

                }

            }

            @Override
            public int getItemCount() {
                return people.size();
            }
        }

        private class MyChildViews extends RecyclerView.ViewHolder {
            TextView contactName;
            CircleImageView contactDp;
            public MyChildViews(final View itemView) {
                super(itemView);
                contactName = (TextView)itemView.findViewById(R.id.contactName);
                contactDp = (CircleImageView)itemView.findViewById(R.id.contactDp);

            }
        }
    }


}
